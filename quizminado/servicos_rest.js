/** 
												################################################
												###   Serviços REST - Projeto: quizminado 	###
												## 				../rest/	content	 			 ##
												################################################ 
 */

/*
	"projeto_quizminado": [
		// SERVIÇOS REST SEM CDI
		{
			"method": "GET",
			"href": "../quizminado/rest/disciplina/listarDisciplinas", // Listar todas as Disciplinas
			"rel": "findAll"
		},
		{
			"method": "POST",
			"href": "../quizminado/rest/disciplina/cadastrarDisciplina", // Cadastrar Disciplina
			"rel": "persist"
			// Content-Type: application/json
			// Payload: {"descricaoDisciplina":"nomeDisciplina"}
		}
		
		// SERVIÇOS REST COM CDI
		{
			"method": "GET",
			"href": "../quizminado/rest/disciplina/getListaDisciplinas", // Listar todas as Disciplinas
			"rel": "findAll"
		},
		{
			// Observação: não cadastra no banco de dados
			"method": "POST",
			"href": "../quizminado/rest/disciplina/salvarDisciplina/post", // Cadastrar Disciplina
			"rel": "persist"
			// Content-Type: application/json
			// Payload: {"descricaoDisciplina":"nomeDisciplina"}
		}
	]
 */ 