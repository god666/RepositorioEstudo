package br.com.projetomvc.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.projetomvc.entity.Client;

@Repository
public class ClientDAO extends GenericDAO<Client> implements InterfaceClientDAO<Client>{

	@Override
	public Client findByUsername(String username) {
		try{
			Query q = em.createQuery("select c from Client c where c.username=:usernameParam");
			q.setParameter("usernameParam", username);
			return (Client) q.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
}