package model.dao;

import java.util.List;

import model.entities.Responsavel;

public interface ResponsavelDao {

	void insert(Responsavel obj);
	void update(Responsavel obj);
	void deleteById(Integer id);
	Responsavel findById(Integer id);
	List<Responsavel> findAll();
}
