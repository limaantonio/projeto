package model.dao;

import db.DB;
import model.dao.impl.AssociacaoDaoJDBC;
import model.dao.impl.ClienteDaoJDBC;

public class DaoFactory {

	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
	public static AssociacaoDao createAssocicaoDao() {
		return new AssociacaoDaoJDBC(DB.getConnection());
	}
}
