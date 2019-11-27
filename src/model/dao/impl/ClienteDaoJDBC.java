package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.ClienteDao;
import model.entities.Associacao;
import model.entities.Cliente;

public class ClienteDaoJDBC implements ClienteDao {

	private Connection conn;
	
	public ClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Cliente obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO cliente "
					+ "(Name, DataNascimento, CPF, AssociacaoId) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setDate(2, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setString(3, obj.getCpf());
			st.setInt(4, obj.getAssociacao().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Cliente obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE cliente "
					+ "SET Name = ?, DataNascimento = ?, Endereco = ?, CPF = ?, AssociacaoId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getNome());
			st.setDate(2, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setString(3, obj.getCpf());
			st.setInt(4, obj.getAssociacao().getId());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM cliente WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Cliente findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT cliente.*,Associacao.Name as DepName "
					+ "FROM cliente INNER JOIN Associacao "
					+ "ON cliente.AssociacaoId = Associacao.Id "
					+ "WHERE cliente.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Associacao dep = instantiateAssociacao(rs);
				Cliente obj = instantiateCliente(rs, dep);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Cliente instantiateCliente(ResultSet rs, Associacao dep) throws SQLException {
		Cliente obj = new Cliente();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Name"));
	//	obj.setDataNascimento(new java.util.Date(rs.getTimestamp("BirthDate").getTime()));
		obj.setCpf(rs.getString("CPF"));
		obj.setAssociacao(dep);
		
		return obj;
	}

	private Associacao instantiateAssociacao(ResultSet rs) throws SQLException {
		Associacao dep = new Associacao();
		dep.setId(rs.getInt("AssociacaoId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Cliente> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM cliente ORDER BY Name");
			rs = st.executeQuery();

			List<Cliente> list = new ArrayList<>();

			while (rs.next()) {
				Cliente obj = new Cliente();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Name"));
				obj.setDataNascimento(rs.getDate("DataNascimento"));
				
				obj.setCpf(rs.getString("CPF"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}


	public List<Cliente> findByAssociacao(Associacao Associacao) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT cliente.*,associacao.Name as DepName "
					+ "FROM cliente INNER JOIN Associacao "
					+ "ON cliente.AssociacaoId = Associacao.Id "
					+ "WHERE AssociacaoId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, Associacao.getId());
			
			rs = st.executeQuery();
			
			List<Cliente> list = new ArrayList<>();
			Map<Integer, Associacao> map = new HashMap<>();
			
			while (rs.next()) {
				
				Associacao dep = map.get(rs.getInt("AssociacaoId"));
				
				if (dep == null) {
					dep = instantiateAssociacao(rs);
					map.put(rs.getInt("AssociacaoId"), dep);
				}
				
				Cliente obj = instantiateCliente(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
