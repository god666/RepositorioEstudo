package br.com.projetospring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetospring.entidade.Usuario;

/*A annotation @Repository indica que será criado um bean no 
 * arquivo applicationContext.xml*/
@Repository
public class UsuarioDAO implements InterfaceGenericoDAO<Usuario> {

	/*A annotation @PersistenceContext indica que o SPRING irá fazer a injeção
	 * (da Injeção de Dependência) do EntityManager para o DAO*/
	@PersistenceContext
	private EntityManager em;
	
	public UsuarioDAO(EntityManager em){
		this.em = em;
	}
	
	//Este contrutor é necessário para estrutura a classe UsuarioDAO como uma classe bean
	public UsuarioDAO() {}
	
	@Transactional
	@Override
	public Usuario salvar(Usuario usuario) {
		return em.merge(usuario);
	}

	@Transactional
	@Override
	public void excluir(Usuario usuario) {
		em.remove(usuario);		
	}

	@Override
	public Usuario buscarPorId(int id) {
		Usuario retorno = em.find(Usuario.class, id);
		return retorno;
	}

	@Override
	public List<Usuario> buscarTodos() {
		Query q = em.createQuery("select u from Usuario u");
		return q.getResultList();
	}

}
