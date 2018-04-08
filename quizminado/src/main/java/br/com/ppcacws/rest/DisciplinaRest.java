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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.ppcacws.model.Disciplina;
import br.com.ppcacws.repository.DisciplinaRepository;
import br.com.ppcacws.service.DisciplinaService;
import br.com.ppcacws.vo.DisciplinaVo;

@Path("/disciplina")
@RequestScoped
public class DisciplinaRest {

	@Inject
	private DisciplinaService disciplinaService;

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg(@PathParam("name") String name) {

		String output = "Welcome   : " + name;

		return Response.status(200).entity(output).build();
	}
	
	/**
	 * EXEMPLOS SEM CDI
	 */
	private final DisciplinaRepository repository = new DisciplinaRepository();

	@GET
	@Path("/listarDisciplinas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListarDisciplinas() {

		List<Disciplina> listaDisciplinas = repository.listarDisciplinas();
		
		List<DisciplinaVo> lista = DisciplinaVo.popularDisciplinas(listaDisciplinas);

		GenericEntity<List<DisciplinaVo>> entity = new GenericEntity<List<DisciplinaVo>>(lista) {};

		return Response.ok(entity).build();
	}

	// SALVA NO BANCO
	@POST
	@Path("/cadastrarDisciplina")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarDisciplina(DisciplinaVo disciplina) {

		Disciplina disciplinaEntity = new Disciplina(disciplina.getDescricaoDisciplina());

		repository.salvar(disciplinaEntity);

		String output = "OK!";

		return Response.status(200).entity(output).build();
	}
	

	/**
	 * EXEMPLOS COM CDI
	 */
	@GET
	@Path("/getListaDisciplinas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaDisciplinas() {

		List<Disciplina> listaDisciplinas = disciplinaService.getListaDisciplinas();

		List<DisciplinaVo> lista = DisciplinaVo.popularDisciplinas(listaDisciplinas);

		GenericEntity<List<DisciplinaVo>> entity = new GenericEntity<List<DisciplinaVo>>(lista) {};

		return Response.ok(entity).build();
	}

	// NÃO SALVA NO BANCO
	@POST
	@Path("/salvarDisciplina/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarDisciplina(DisciplinaVo disciplina) {

		Disciplina disciplinaEntity = new Disciplina(disciplina.getDescricaoDisciplina());

		disciplinaService.salvar(disciplinaEntity);

		String output = "OK!";

		return Response.status(200).entity(output).build();
	}

}
