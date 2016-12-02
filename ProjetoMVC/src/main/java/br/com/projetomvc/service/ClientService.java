package br.com.projetomvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetomvc.dao.ClientDAO;
import br.com.projetomvc.dao.DAOException;
import br.com.projetomvc.dao.InterfaceClientDAO;
import br.com.projetomvc.entity.Client;
import br.com.projetomvc.util.MessageUtil;

@Service
public class ClientService {
	
	@Autowired
	@Qualifier("clientDAO")
	InterfaceClientDAO<Client> dao;
	
	public Client save(Client client){
		Client clientMustBeNull = dao.findByUsername(client.getUsername());
		
		/*If clientMustBeNull return null, the username is not in the Database. So, 
		 * the system should allow the insertion*/
		if (clientMustBeNull==null){
			return dao.save(client);
		} else {
			return null;
		}
		
	}

	public void delete(Client client) throws DAOException {
		dao.delete(client);
	}

	public List<Client> findAll() {
		return dao.findAll();
	}
}