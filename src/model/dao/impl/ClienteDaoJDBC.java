package model.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Statement;

import db.DB;
import db.DbException;
import model.dao.ClienteDao;
import model.entities.Associacao;
import model.entities.Cliente;


public  class ClienteDaoJDBC implements ClienteDao {

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
					+ "(Name, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNomeCliente());
			//st.setInt(2, obj.getAssociacao().getId());
			
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

//	@Override
//	public void update(Cliente obj) {
//		PreparedStatement st = null;
//		try {
//			st = conn.prepareStatement(
//					"UPDATE seller "
//					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
//					+ "WHERE Id = ?");
//			
//			st.setString(1, obj.getName());
//			st.setString(2, obj.getEmail());
//			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
//			st.setDouble(4, obj.getBaseSalary());
//			st.setInt(5, obj.getDepartment().getId());
//			st.setInt(6, obj.getId());
//			
//			st.executeUpdate();
//		}
//		catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		}
//		finally {
//			DB.closeStatement(st);
//		}
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		PreparedStatement st = null;
//		try {
//			st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
//			
//			st.setInt(1, id);
//			
//			st.executeUpdate();
//		}
//		catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		}
//		finally {
//			DB.closeStatement(st);
//		}
//	}
//
//	@Override
//	public Cliente findById(Integer id) {
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		try {
//			st = conn.prepareStatement(
//					"SELECT seller.*,department.Name as DepName "
//					+ "FROM seller INNER JOIN department "
//					+ "ON seller.DepartmentId = department.Id "
//					+ "WHERE seller.Id = ?");
//			
//			st.setInt(1, id);
//			rs = st.executeQuery();
//			if (rs.next()) {
//				Department dep = instantiateDepartment(rs);
//				Seller obj = instantiateSeller(rs, dep);
//				return obj;
//			}
//			return null;
//		}
//		catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		}
//		finally {
//			DB.closeStatement(st);
//			DB.closeResultSet(rs);
//		}
//	}
//
//	private Cliente instantiateSeller(ResultSet rs, Department dep) throws SQLException {
//		Cliente obj = new Seller();
//		obj.setId(rs.getInt("Id"));
//		obj.setName(rs.getString("Name"));
//		obj.setEmail(rs.getString("Email"));
//		obj.setBaseSalary(rs.getDouble("baseSalary"));
//		obj.setBirthDate(new java.util.Date(rs.getTimestamp("BirthDate").getTime()));
//		obj.setDepartment(dep);
//		return obj;
//	}
//
//	private Department instantiateDepartment(ResultSet rs) throws SQLException {
//		Department dep = new Department();
//		dep.setId(rs.getInt("DepartmentId"));
//		dep.setName(rs.getString("DepName"));
//		return dep;
//	}
//
//	@Override
//	public List<Cliente> findAll() {
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		try {
//			st = conn.prepareStatement(
//					"SELECT seller.*,department.Name as DepName "
//					+ "FROM seller INNER JOIN department "
//					+ "ON seller.DepartmentId = department.Id "
//					+ "ORDER BY Name");
//			
//			rs = st.executeQuery();
//			
//			List<Cliente> list = new ArrayList<>();
//			Map<Integer, Department> map = new HashMap<>();
//			
//			while (rs.next()) {
//				
//				Department dep = map.get(rs.getInt("DepartmentId"));
//				
//				if (dep == null) {
//					dep = instantiateDepartment(rs);
//					map.put(rs.getInt("DepartmentId"), dep);
//				}
//				
//				Seller obj = instantiateSeller(rs, dep);
//				list.add(obj);
//			}
//			return list;
//		}
//		catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		}
//		finally {
//			DB.closeStatement(st);
//			DB.closeResultSet(rs);
//		}
//	}
//
//	@Override
//	public List<Cliente> findByDepartment(Department department) {
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		try {
//			st = conn.prepareStatement(
//					"SELECT seller.*,department.Name as DepName "
//					+ "FROM seller INNER JOIN department "
//					+ "ON seller.DepartmentId = department.Id "
//					+ "WHERE DepartmentId = ? "
//					+ "ORDER BY Name");
//			
//			st.setInt(1, department.getId());
//			
//			rs = st.executeQuery();
//			
//			List<Cliente> list = new ArrayList<>();
//			Map<Integer, Department> map = new HashMap<>();
//			
//			while (rs.next()) {
//				
//				Department dep = map.get(rs.getInt("DepartmentId"));
//				
//				if (dep == null) {
//					dep = instantiateDepartment(rs);
//					map.put(rs.getInt("DepartmentId"), dep);
//				}
//				
//				Cliente obj = instantiateSeller(rs, dep);
//				list.add(obj);
//			}
//			return list;
//		}
//		catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		}
//		finally {
//			DB.closeStatement(st);
//			DB.closeResultSet(rs);
//		}
//	}
//
//	
	
	@Override
	public List<Cliente> findByAssociacao(Associacao associacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cliente obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
