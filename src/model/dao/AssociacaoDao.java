package model.dao;

import java.util.List;

import model.entities.Associacao;

public interface AssociacaoDao {

	void insert(Associacao obj);
	void update(Associacao obj);
	void deleteById(Integer id);
	Associacao findById(Integer id);
	List<Associacao> findAll();
}
