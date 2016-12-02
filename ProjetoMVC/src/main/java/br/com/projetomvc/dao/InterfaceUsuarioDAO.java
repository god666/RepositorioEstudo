package br.com.projetomvc.dao;

import java.util.List;

public interface InterfaceUsuarioDAO <User> extends InterfaceGenericDAO<User>{
	
	public User findByUsername(String username);
}
