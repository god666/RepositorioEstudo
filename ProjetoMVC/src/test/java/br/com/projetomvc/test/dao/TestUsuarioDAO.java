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
import br.com.projetomvc.dao.InterfaceUsuarioDAO;
import br.com.projetomvc.dao.UsuarioDAO;
import br.com.projetomvc.entity.Usuario;

import java.util.List;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/applicationContext.xml")
public class TestUsuarioDAO {
	
	@Autowired
	@Qualifier("usuarioDAO")
	private InterfaceUsuarioDAO<Usuario> dao;
	
	@Test
	@Transactional
	public void testSave(){
		Usuario usuario = new Usuario();
		usuario.setName("name save");
		usuario.setPassword("password save");
		usuario.setUsername("username save");

		Usuario savedUsuario = dao.save(usuario);	
		
		
		Assert.assertEquals(usuario.getName(), savedUsuario.getName());
		Assert.assertEquals(usuario.getPassword(), savedUsuario.getPassword());
		Assert.assertEquals(usuario.getUsername(), savedUsuario.getUsername());
		Assert.assertNotNull(savedUsuario.getId());
	}
	
	@Test
	@Transactional
	public void testDelete(){
		Usuario usuario = new Usuario();
		usuario.setName("name delete");
		usuario.setPassword("password delete");
		usuario.setUsername("username delete");
		Usuario savedUsuario = dao.save(usuario);	
		
		Integer id = savedUsuario.getId();
		try {
			dao.delete(savedUsuario);
		} catch (DAOException e) {
			System.out.println("Problema");
			e.printStackTrace();
		}
		Usuario deletedUsuario = dao.findById(id);
		Assert.assertNull(deletedUsuario);
	}
	
	public void testFindById(){
		Usuario usuario = new Usuario();
		usuario.setName("name findid");
		usuario.setPassword("password findid");
		usuario.setUsername("username findid");
		Usuario savedUsuario = dao.save(usuario);
		
		Integer id = savedUsuario.getId();
		Usuario foundUsuario = dao.findById(id);
		
		Assert.assertEquals(savedUsuario, foundUsuario);
	}
	
	public void testFindAll(){
		Usuario usuario = new Usuario();
		usuario.setName("name findall");
		usuario.setPassword("password findall");
		usuario.setUsername("username findall");
		dao.save(usuario);
		
		List<Usuario> lista = dao.findAll();
		
		Assert.assertTrue(lista.size()>0);
	}
	
	public void testFindByUsername(){
		
	}
	
}
