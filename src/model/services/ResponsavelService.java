package model.services;

import java.util.List;

import model.dao.ResponsavelDao;
import model.dao.DaoFactory;
import model.entities.Responsavel;


public class ResponsavelService {
	
	private ResponsavelDao dao = DaoFactory.createResponsavelDao();
	
	public List<Responsavel> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Responsavel obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Responsavel obj) {
		dao.deleteById(obj.getId());
	}
}	
