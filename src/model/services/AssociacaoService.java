package model.services;

import java.util.List;

import model.dao.AssociacaoDao;
import model.dao.DaoFactory;
import model.entities.Associacao;


public class AssociacaoService {
	
	private AssociacaoDao dao = DaoFactory.createAssocicaoDao();
	
	public List<Associacao> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Associacao obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Associacao obj) {
		dao.deleteById(obj.getId());
	}
}	
