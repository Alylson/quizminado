package br.com.ppcacws.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.ppcacws.model.Disciplina;
import br.com.ppcacws.service.DisciplinaService;

@Path("/hello")
@RequestScoped
public class HelloWorldService {
	
	@Inject
	private DisciplinaService disciplinaService;
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg(@PathParam("name") String name) {

		String output = "Welcome   : " + name;

		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/disciplinas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDisciplinas() {
		
		List<Disciplina> listaDisciplinas = disciplinaService.getListaDisciplinas();
		
		for(Disciplina d : listaDisciplinas) {
			
			System.out.println("id: " + d.getIdDisciplina());
			System.out.println("descrição: " + d.getDescricaoDisciplina());
		}

		String output = "OK!";

		return Response.status(200).entity(output).build();
	}
	

	@POST
	@Path("/disciplina/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarDisciplina(Disciplina disciplina) {
		
		//Disciplina disciplina = new Disciplina("Matemática");
		
		disciplinaService.salvar(disciplina);
		
		String output = "OK!";

		return Response.status(200).entity(output).build();
	}

}
