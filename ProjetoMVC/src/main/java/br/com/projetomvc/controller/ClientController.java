package br.com.projetomvc.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.projetomvc.dao.DAOException;
import br.com.projetomvc.entity.Client;
import br.com.projetomvc.service.ClientService;
import br.com.projetomvc.util.MessageUtil;

@Controller(value="clientController")
public class ClientController {

	private Client client = new Client();
	private List<Client> clientList;
	
	@Autowired
	ClientService service;
	
	@PostConstruct
	public void init(){
		clientList = service.findAll();
	}
	
	public void save(){
		Client savedClient = service.save(client);
		MessageUtil.sucessMessage(MessageUtil.SAVE_SUCESS);
		/*It will occor only when a new client was registered (ID is null). A update will not 
		 * refresh the list*/
		if(client.getId()==null){
			clientList.add(savedClient);
		}
		client = new Client();
		
	}
	
	public void delete(Client client){
		try {
			service.delete(client);
			clientList.remove(client);
			MessageUtil.sucessMessage(MessageUtil.DELETE_SUCESS);
		} catch (DAOException e) {
			MessageUtil.errorMessage(MessageUtil.DELETE_ERROR);
			e.printStackTrace();
		}
		
	}
	
	//It will fill the register field
	public void edit(Client client){
		this.client = client;
	}
	
	/*Get and Sets*/
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
	
	
}
