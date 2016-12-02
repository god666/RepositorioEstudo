package br.com.projetomvc.test.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetomvc.dao.DAOException;
import br.com.projetomvc.dao.InterfaceClientDAO;
import br.com.projetomvc.dao.ClientDAO;
import br.com.projetomvc.entity.Client;

import java.util.List;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/applicationContext.xml")
public class TestClientDAO {
	
	@Autowired
	@Qualifier("clientDAO")
	private InterfaceClientDAO<Client> dao;
	
	@Test
	@Transactional
	public void testSave(){
		Client client = new Client();
		client.setName("name save");
		client.setPassword("password save");
		client.setUsername("username save");

		Client savedClient = dao.save(client);	
		
		
		Assert.assertEquals(client.getName(), savedClient.getName());
		Assert.assertEquals(client.getPassword(), savedClient.getPassword());
		Assert.assertEquals(client.getUsername(), savedClient.getUsername());
		Assert.assertNotNull(savedClient.getId());
	}
	
	@Test
	@Transactional
	public void testDelete() throws DAOException{
		Client client = new Client();
		client.setName("name delete");
		client.setPassword("password delete");
		client.setUsername("username delete");
		Client savedClient = dao.save(client);	
		
		Integer id = savedClient.getId();
		
		try {
			dao.delete(savedClient);
		} catch (DAOException e) {
			System.out.println("Problema");
			e.printStackTrace();
		}
		Client deletedClient = dao.findById(id);
		Assert.assertNull(deletedClient);
	}
	
	@Test
	@Transactional
	public void testFindById(){
		Client client = new Client();
		client.setName("name findid");
		client.setPassword("password findid");
		client.setUsername("username findid");
		Client savedClient = dao.save(client);
		
		Integer id = savedClient.getId();
		Client foundClient = dao.findById(id);
		
		Assert.assertEquals(savedClient, foundClient);
	}
	
	@Test
	@Transactional
	public void testFindAll(){
		Client client = new Client();
		client.setName("name findall");
		client.setPassword("password findall");
		client.setUsername("username findall");
		dao.save(client);
		
		List<Client> lista = dao.findAll();
		
		Assert.assertTrue(lista.size()>0);
	}
	
	@Test
	@Transactional
	public void testFindByUsername(){
		Client client = new Client();
		String username = "username findusername";
		client.setName("name findusername");
		client.setPassword("password findusername");
		client.setUsername(username);
		Client savedClient = dao.save(client);
		
		Client foundClient = dao.findByUsername(username);
		
		Assert.assertEquals(savedClient, foundClient);
	}
	
}
