package br.com.ppcacws.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.ppcacws.model.Disciplina;

public class DisciplinaRepository {

	private static EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	
	
	static {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("quizminado");
	}
	
	public DisciplinaRepository() {
		
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void salvar(Disciplina disciplina) {
		
		try {
			
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(disciplina);
			this.entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			
			this.entityManager.getTransaction().rollback();
			
			e.printStackTrace();
		
		} finally {

			this.entityManager.close();
		}
	}
	
	public Disciplina alterar(Disciplina disciplina) {
		
		try {
			
			this.entityManager.getTransaction().begin();
			disciplina = (Disciplina) this.entityManager.merge(disciplina);
			this.entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			
			this.entityManager.getTransaction().rollback();
			
			e.printStackTrace();
		
		} finally {

			this.entityManager.close();
		}
		
		return disciplina;
	}
	
	@SuppressWarnings("unchecked")
	public List<Disciplina> listarDisciplinas() {
		
		List<Disciplina> listaDisciplina = null;
		
		try {
			
			Query query = this.entityManager.createQuery("SELECT d FROM Disciplina d ORDER BY d.descricaoDisciplina");
			
			listaDisciplina = (List<Disciplina>) query.getResultList();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		} finally {
			
			this.entityManager.close();
		}
		
		return listaDisciplina;
	}
	
	public Disciplina getDisciplina(Integer codigo) {
		
		Disciplina disciplina = null;

		try {

			disciplina = (Disciplina) this.entityManager.find(Disciplina.class, codigo);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			this.entityManager.close();
		}

		return disciplina;
	}
	
	public void excluir(Integer codigo) {
		
		Disciplina disciplina = this.getDisciplina(codigo);
		
		try {
			
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(disciplina);
			this.entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			
			this.entityManager.getTransaction().rollback();
			
			e.printStackTrace();
		
		} finally {

			this.entityManager.close();
		}
	}
	
}
