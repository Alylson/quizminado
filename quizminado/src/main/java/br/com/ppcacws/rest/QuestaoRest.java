package br.com.ppcacws.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import br.com.ppcacws.model.Questao;
import br.com.ppcacws.repository.QuestaoRepository;
import br.com.ppcacws.service.QuestaoService;
import br.com.ppcacws.vo.QuestaoVo;

@Path("/questao")
@RequestScoped
public class QuestaoRest {

	@Inject
	private QuestaoService questaoService;
	
	private final QuestaoRepository questaoRepository = new QuestaoRepository();


	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarQuestaoPorId(@PathParam("id") String id) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		QuestaoVo questao = null;
		
		GenericEntity<QuestaoVo> entity = null;
		
		try {
			
			Questao questaoEntity = questaoRepository.getQuestao(Integer.parseInt(id));
			
			questao = QuestaoVo.clone(questaoEntity);
			
			entity = new GenericEntity<QuestaoVo>(questao) {};
			
			return Response.ok(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Nenhuma Questão encontrada para o Id: " + id);
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}
	
	@GET
	@Path("/listarQuestoes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListarQuestoes() {

		List<Questao> listaQuestoes = questaoRepository.listarQuestoes();

		List<QuestaoVo> lista = QuestaoVo.popularQuestoes(listaQuestoes);

		GenericEntity<List<QuestaoVo>> entity = new GenericEntity<List<QuestaoVo>>(lista) {};
		
		return Response.ok(entity).build();
	}

}
