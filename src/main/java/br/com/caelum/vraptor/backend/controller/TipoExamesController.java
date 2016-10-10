package br.com.caelum.vraptor.backend.controller;


import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.backend.business.TipoExameLogic;
import br.com.caelum.vraptor.backend.model.TipoExame;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;

/**
 * @author fidelis.guimaraes
 *
 */
@Path("/tipoexames")
@Controller
public class TipoExamesController {

	private final Result result;
	private HttpServletRequest req;
	@Inject
	private ServletContext context;
	@Inject
	private Router router;
	@Inject
	private TipoExameLogic logic;
	/**
	 * @deprecated CDI eyes only
	 */
	protected TipoExamesController() {
		this(null, null, null);
	}
	
	@Inject
	public TipoExamesController(Result result, HttpServletRequest req, TipoExameLogic logic) {
		this.result = result;
		this.req = req;
		this.logic = logic;
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path({"", "/"})
	public void tipoExameList() {
		result.use(Results.json()).from(logic.listAll(), "tipoExameList").serialize();
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/{tipoExame.id}")
	public void tipoExameUnique(TipoExame tipoExame) {
		result.use(Results.json()).from(logic.load(tipoExame.getId()), "tipoExame").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/add")
	public void add(TipoExame tipoExame) {
		this.logic.persist(tipoExame);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/remove")
	public void remove(Long id) {
		TipoExame remove = new TipoExame();
		remove.setId(id);
		this.logic.remove(remove);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path({"", "/"})
	public void edit(TipoExame tipoExame) {
		this.logic.update(tipoExame);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
}