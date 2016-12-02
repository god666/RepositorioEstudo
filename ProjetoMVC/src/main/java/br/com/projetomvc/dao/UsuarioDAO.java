package br.com.projetomvc.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.projetomvc.entity.Usuario;

@Repository
public class UsuarioDAO extends GenericDAO<Usuario> implements InterfaceUsuarioDAO<Usuario>{

	@Override
	public Usuario findByUsername(String username) {
		Query q = em.createQuery("select u from Usuario u where u.username=:usernameParam");
		q.setParameter("usernameParam", username);
		return (Usuario) q.getSingleResult();
	}
	
}