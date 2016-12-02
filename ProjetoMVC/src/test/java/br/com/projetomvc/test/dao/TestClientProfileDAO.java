package br.com.projetomvc.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetomvc.dao.ClientProfileDAO;
import br.com.projetomvc.dao.InterfaceClientProfileDAO;
import br.com.projetomvc.entity.ClientProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TestClientProfileDAO {

	@Autowired
	@Qualifier("clientProfileDAO")
	InterfaceClientProfileDAO<ClientProfile> dao;
	
	@Test
	@Transactional
	public void testSave(){
		ClientProfile profile = new ClientProfile();
		profile.setDescription("CONTADOR");
		ClientProfile savedProfile = dao.save(profile);
		
		Assert.assertEquals(profile.getDescription(), savedProfile.getDescription());
		Assert.assertNotNull(savedProfile.getId());
		
	}
}
