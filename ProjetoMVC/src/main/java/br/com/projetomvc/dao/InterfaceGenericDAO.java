package br.com.projetomvc.dao;

import java.util.List;

import br.com.projetomvc.exception.DAOException;

public interface InterfaceGenericDAO <Objeto>{
	
	public Objeto save(Objeto obj);
	public void delete(Objeto obj) throws DAOException;
	public Objeto findById(Integer id);
	public List<Objeto> findAll();

}
