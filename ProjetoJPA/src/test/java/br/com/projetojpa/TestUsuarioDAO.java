package br.com.projetojpa;

import org.junit.Before;
import org.junit.Test;

import br.com.projetojpa.dao.UsuarioDAO;
import br.com.projetojpa.entidade.Usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;

public class TestUsuarioDAO {
	
	private UsuarioDAO dao;
	private EntityManager em;
	
	@Before
	public void init(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				"projetojpa");
		em = emf.createEntityManager();
		
		dao = new UsuarioDAO(em);
	}
	
	@After
	public void finish(){
		em.close();
	}
	
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
