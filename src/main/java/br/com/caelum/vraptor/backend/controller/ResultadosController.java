package br.com.caelum.vraptor.backend.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.backend.business.ResultadoExameLogic;
import br.com.caelum.vraptor.backend.model.ResultadoExame;
import br.com.caelum.vraptor.view.Results;

/**
 * @author fidelis.guimaraes
 *
 */
@Path("/resultados")
@Controller
public class ResultadosController {

	private Result result;
	private ResultadoExameLogic logic;

	protected ResultadosController() {
	}
	
	@Inject
	public ResultadosController (Result result, ResultadoExameLogic logic){
		this.result = result;
		this.logic = logic;
	}

	@Consumes("application/json")
	@Post
	@Path("/add")
	public void add(ResultadoExame resultado) {
		this.logic.persist(resultado);
		this.result.use(Results.json()).from("OK").serialize();
	}
	@Consumes("application/json")
	@Get
	@Path({"", "/"})
	public void list() {
		this.result.use(Results.json()).from(this.logic.listAll(), "resultadoList").serialize();
	}
	@Consumes("application/json")
	@Get
	@Path("/{resultado.id}")
	public void testLoad(ResultadoExame resultado) {
		this.result.use(Results.json()).from(this.logic.load(resultado.getId()), "resultado").serialize();
	}
	@Consumes("application/json")
	@Put
	@Path("/{resultado.id}")
	public void testUpdate(ResultadoExame resultado) {
		this.logic.update(resultado);
		this.result.use(Results.json()).from("OK").serialize();
	}
	@Transactional
	@Consumes("application/json")
	@Delete
	@Path("/{resultado.id}")
	public void testRemove(ResultadoExame resultado) {
		this.logic.delete(resultado);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
}
