package br.com.projetomvc.dao;

public interface InterfaceClientDAO <Client> extends InterfaceGenericDAO<Client>{
	
	public Client findByUsername(String username);
}
