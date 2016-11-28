package br.com.projetospring.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.projetospring.dao.UsuarioDAO;
import br.com.projetospring.entidade.Usuario;

public class ddddd {

	@Autowired
	static UsuarioDAO dao;
	
	public static void main(String[] args) {
		
		testSalvar();
		
	}

	
	public static void testSalvar(){
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext(
				"file:/Users/god/workspace/ProjetoSpring/src/main/resources/META-INF/applicationContext.xml");
		
		EntityManager em;
		EntityManagerFactory emf = (EntityManagerFactory) cxt.getBean("entityManagerFactory");
				em = emf.createEntityManager();
		
		dao = new UsuarioDAO(em);
		
		Usuario usuario = new Usuario();
		usuario.setNome("TesteNomeSalvar");
		usuario.setLogin("testeLoginSalvar");
		usuario.setSenha("123Salvar");
		
		Usuario usuarioSalvo = dao.salvar(usuario);
		usuarioSalvo = dao.buscarPorId(1);
		System.out.println(usuarioSalvo);
	}
}
