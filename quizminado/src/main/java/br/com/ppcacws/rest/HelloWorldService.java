package br.com.ppcacws.rest;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.ppcacws.model.Disciplina;

@Path("/hello")
public class HelloWorldService {

	
	@GET
	@Path("/{name}")
	public Response getMsg(@PathParam("name") String name) {

		String output = "Welcome   : " + name;

		return Response.status(200).entity(output).build();
	}
	
	
	@GET
	@Path("/disciplinas")
	@SuppressWarnings("unchecked")
	public Response getDisciplinas() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("quizminado");

		List<Disciplina> listaDisciplina = null;
		
		try {
			
			Query query = emf.createEntityManager().createQuery("FROM Disciplina");
			
			listaDisciplina = (List<Disciplina>) query.getResultList();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		} finally {
			
			emf.close();
		}
		
		for(Disciplina d : listaDisciplina) {
			
			System.out.println("id: " + d.getIdDisciplina());
			System.out.println("descrição: " + d.getDescricaoDisciplina());
		}

		String output = "OK!";

		return Response.status(200).entity(output).build();
	}

}
