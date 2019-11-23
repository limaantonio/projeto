package model.dao;

import db.DB;
import model.dao.impl.AssociacaoDaoJDBC;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.ResponsavelDaoJDBC;

public class DaoFactory {

	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
	public static AssociacaoDao createAssocicaoDao() {
		return new AssociacaoDaoJDBC(DB.getConnection());
	}
	
	public static ResponsavelDao createResponsavelDao() {
		return new ResponsavelDaoJDBC(DB.getConnection());
	}
}
