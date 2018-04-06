package br.com.ppcacws.dao;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Resource
public class CRUDImplDao<T> implements CRUDDao<T> {

	@Inject
	private EntityManager em;
	
	private Class<T> entidade;
	
	
	public CRUDImplDao() {
		
	}
	
	public CRUDImplDao(Class<T> entidade) {
		
		this.entidade = entidade;
	}
	
	
	@Override
	public void salvar(T Entidade) {
		
		try {
			
			em.getTransaction().begin();
			
			em.clear();
			
			em.persist(entidade);
			
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			
			e.printStackTrace();
			
		} finally {
			
			em.close();
		}
	}

	@Override
	public T recuperar(Integer id) {
		
		T t = null;
		
		try {
			
			t = (T) em.find(entidade, id);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			em.close();
		}
		
		return t;
	}

	@Override
	public T atualizar(T entidade) {
		
		try {
			
			em.getTransaction().begin();
			
			em.clear();
			
			entidade = em.merge(entidade);
			
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			
			e.printStackTrace();
			
		} finally {
			
			em.close();
		}
		
		return entidade;
	}

	@Override
	public void deletar(T entidade) {
		
		try {
			
			em.getTransaction().begin();
			
			em.remove(entidade);
			
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			
			e.printStackTrace();
			
		} finally {
			
			em.close();
		}
	}
	
}
