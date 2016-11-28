package br.com.projetospring.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetospring.dao.InterfaceGenericoDAO;
import br.com.projetospring.dao.UsuarioDAO;
import br.com.projetospring.entidade.Usuario;
import javassist.ClassPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/resources/META-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestUsuarioDAO {

	@Autowired
	@Qualifier("usuarioDAO")
	private InterfaceGenericoDAO<Usuario> dao;
	
	@Test
	public void testSalvar(){
		
		Usuario usuario = new Usuario();
		usuario.setNome("TesteNomeSalvar");
		usuario.setLogin("testeLoginSalvar");
		usuario.setSenha("123Salvar");
		
		Usuario usuarioSalvo = dao.salvar(usuario);
		
		Assert.assertEquals(usuario.getNome(), usuarioSalvo.getNome());
		Assert.assertEquals(usuario.getLogin(), usuarioSalvo.getLogin());
		Assert.assertEquals(usuario.getSenha(), usuarioSalvo.getSenha());
		Assert.assertNotNull(usuarioSalvo.getId());
	}

	/*Foi colocado a annotation @Transaction para evitar uma exception de perda de
	 * contexto do objeto. Neste método existem dois commits. O commit muda o estado
	 * do objeto para despached.*/
	@Transactional
	@Test
	public void testExcluir(){
		Usuario usuario = new Usuario();
		usuario.setNome("TesteNomeExcluir");
		usuario.setLogin("testeLoginExcluir");
		usuario.setSenha("123Excluir");
		
		Usuario usuarioSalvo = dao.salvar(usuario);
		
		int id = usuarioSalvo.getId();
		dao.excluir(usuarioSalvo);
		
		Usuario usuarioExcluido = dao.buscarPorId(id);
		
		Assert.assertNull(usuarioExcluido);
	}

	@Test
	public void testBuscaPorId(){
		Usuario usuario = new Usuario();
		usuario.setNome("TesteNomeBuscarPorId");
		usuario.setLogin("testeLoginBuscarPorId");
		usuario.setSenha("123BuscarPorId");
		
		Usuario usuarioSalvo = dao.salvar(usuario);
		
		int id = usuarioSalvo.getId();
		Usuario usuarioBuscado = dao.buscarPorId(id);
		
		Assert.assertEquals(usuarioSalvo, usuarioBuscado);
	}
	
	@Test
	public void testBuscarTodos(){
		Usuario usuario = new Usuario();
		usuario.setNome("TesteNomeBuscarTodos");
		usuario.setLogin("testeLoginBuscarTodos");
		usuario.setSenha("123BuscarPorTodos");
		
		Usuario usuarioSalvo = dao.salvar(usuario);
		
		List<Usuario> listaUsuario = dao.buscarTodos();
		
		Assert.assertTrue(listaUsuario.size()>0);
	}
}
