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
import model.dao.ResponsavelDao;
import model.entities.Associacao;
import model.entities.Responsavel;

public class ResponsavelDaoJDBC implements ResponsavelDao {

	private Connection conn;
	
	public ResponsavelDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Responsavel obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO responsavel "
					+ "(Name, DataNascimento, Endereco, CPF, AssociacaoId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setDate(2, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setString(3, obj.getEndereco());
			st.setString(4, obj.getCpf());
			st.setInt(5, obj.getAssociacao().getId());
			
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
	public void update(Responsavel obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE responsavel "
					+ "SET Name = ?, DataNascimento = ?, Endereco = ?, CPF = ?, AssociacaoId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getNome());
			st.setDate(2, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setString(3, obj.getEndereco());
			st.setString(4, obj.getCpf());
			st.setInt(5, obj.getAssociacao().getId());
			st.setInt(6, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM responsavel WHERE Id = ?");
			
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
	public Responsavel findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Responsavel.*,Associacao.Name as DepName "
					+ "FROM Responsavel INNER JOIN Associacao "
					+ "ON Responsavel.AssociacaoId = Associacao.Id "
					+ "WHERE Responsavel.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Associacao dep = instantiateAssociacao(rs);
				Responsavel obj = instantiateResponsavel(rs, dep);
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

	private Responsavel instantiateResponsavel(ResultSet rs, Associacao dep) throws SQLException {
		Responsavel obj = new Responsavel();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Name"));
	//	obj.setDataNascimento(new java.util.Date(rs.getTimestamp("BirthDate").getTime()));
		obj.setEndereco(rs.getString("Endereco"));
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
	public List<Responsavel> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM responsavel ORDER BY Name");
			rs = st.executeQuery();

			List<Responsavel> list = new ArrayList<>();

			while (rs.next()) {
				Responsavel obj = new Responsavel();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Name"));
				obj.setDataNascimento(rs.getDate("DataNascimento"));
				obj.setEndereco(rs.getString("Endereco"));
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


	public List<Responsavel> findByAssociacao(Associacao Associacao) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT responsavel.*,associacao.Name as DepName "
					+ "FROM Responsavel INNER JOIN Associacao "
					+ "ON responsavel.AssociacaoId = Associacao.Id "
					+ "WHERE AssociacaoId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, Associacao.getId());
			
			rs = st.executeQuery();
			
			List<Responsavel> list = new ArrayList<>();
			Map<Integer, Associacao> map = new HashMap<>();
			
			while (rs.next()) {
				
				Associacao dep = map.get(rs.getInt("AssociacaoId"));
				
				if (dep == null) {
					dep = instantiateAssociacao(rs);
					map.put(rs.getInt("AssociacaoId"), dep);
				}
				
				Responsavel obj = instantiateResponsavel(rs, dep);
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
