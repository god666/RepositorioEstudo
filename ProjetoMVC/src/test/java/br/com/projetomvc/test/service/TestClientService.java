package br.com.projetomvc.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetomvc.dao.InterfaceClientDAO;
import br.com.projetomvc.entity.Client;
import br.com.projetomvc.service.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TestClientService {
	
	@Autowired
	ClientService service;
	
	@Autowired
	@Qualifier("clientDAO")
	InterfaceClientDAO<Client> dao;
	
	@Transactional
	@Test
	public void mustSave(){
		Client client = new Client();
		client.setName("name save");
		client.setPassword("password save");
		client.setUsername("username save");
		
		Client savedClient= service.save(client);
		
		Assert.assertEquals(client.getName(), savedClient.getName());
		Assert.assertEquals(client.getPassword(), savedClient.getPassword());
		Assert.assertEquals(client.getUsername(), savedClient.getUsername());
		Assert.assertNotNull(savedClient.getId());
	}
	
	@Transactional
	@Test
	public void mustNotSave(){
		Client client = new Client();
		client.setName("name save");
		client.setPassword("password save");
		client.setUsername("username save");
		dao.save(client);
		
		Client savedClient= service.save(client);
		
		Assert.assertNull(savedClient);
	}

}
