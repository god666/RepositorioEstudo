package br.com.projetomvc.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class GenericDAO <Objeto> implements InterfaceGenericDAO<Objeto>{

	@PersistenceContext
	protected EntityManager em;
	
	protected Class<Objeto> param;
	
	public GenericDAO (EntityManager em){
		this.em = em;
	}
	
	public GenericDAO(){
		//This code will get the object inside the class parameter <Objeto>
		param = ((Class) ((ParameterizedType) getClass().
				getGenericSuperclass()).getActualTypeArguments()[0]);

	}
	
	@Override
	@Transactional
	public Objeto save(Objeto obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Objeto obj) throws DAOException {
		try{
			em.remove(obj);
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public Objeto findById(Integer id) {
		return em.find(param, id);
	}

	@Override
	public List<Objeto> findAll() {
		/*It will return the param class name. 
		Ex: If param was Usuario.class, it will return Usuario*/
		String className = param.getSimpleName();
		
		Query q = em.createQuery("select obj from "+className+" obj");
		return q.getResultList();
	}

}
