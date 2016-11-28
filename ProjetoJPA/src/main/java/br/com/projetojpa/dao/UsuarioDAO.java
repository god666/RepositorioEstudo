package br.com.projetojpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projetojpa.entidade.Usuario;

public class UsuarioDAO implements InterfaceGenericoDAO<Usuario>{

	private EntityManager em;
	
	public UsuarioDAO (EntityManager em){
		this.em = em;
	}
	
	//O m�todo salvar retorna um objeto para facilitar o teste. Compensa?
	public Usuario salvar(Usuario usuario) {

		em.getTransaction().begin();
		Usuario retorno = em.merge(usuario);
		em.getTransaction().commit();
		return retorno;
	}

	public void excluir(Usuario usuario) {

		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
		
	}

	public Usuario buscarPorId(int id) {
		
		return em.find(Usuario.class, id);
	}

	public List<Usuario> buscarTodos() {

		Query q = em.createQuery("select u from Usuario u");
		return q.getResultList();
	}

}
