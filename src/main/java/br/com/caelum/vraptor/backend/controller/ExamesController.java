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
import br.com.caelum.vraptor.backend.business.ExameLogic;
import br.com.caelum.vraptor.backend.model.Exame;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;

/**
 * @author fidelis.guimaraes
 *
 */
@Path("/exames")
@Controller
public class ExamesController {

	private final Result result;
	private HttpServletRequest req;
	@Inject
	private ServletContext context;
	@Inject
	private Router router;
	@Inject
	private ExameLogic logic;
	/**
	 * @deprecated CDI eyes only
	 */
	protected ExamesController() {
		this(null, null, null);
	}
	
	@Inject
	public ExamesController(Result result, HttpServletRequest req, ExameLogic logic) {
		this.result = result;
		this.req = req;
		this.logic = logic;
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path({"", "/"})
	public void exameList() {
		result.use(Results.json()).from(logic.listAll(), "exameList").serialize();
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/{exame.id}")
	public void exameUnique(Exame exame) {
		result.use(Results.json()).from(logic.load(exame.getId()), "exame").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/add")
	public void add(Exame exame) {
		this.logic.persist(exame);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/remove")
	public void remove(Long id) {
		Exame remove = new Exame();
		remove.setId(id);
		this.logic.remove(remove);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path({"", "/"})
	public void edit(Exame exame) {
		this.logic.update(exame);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
}