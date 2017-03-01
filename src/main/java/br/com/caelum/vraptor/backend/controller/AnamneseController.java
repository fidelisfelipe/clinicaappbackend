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
import br.com.caelum.vraptor.backend.business.AnamneseLogic;
import br.com.caelum.vraptor.backend.model.Anamnese;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;

/**
 * @author fidelis.guimaraes
 *
 */
@Path("/anamneses")
@Controller
public class AnamneseController {

	private final Result result;
	private HttpServletRequest req;
	@Inject
	private ServletContext context;
	@Inject
	private Router router;
	@Inject
	private AnamneseLogic logic;
	/**
	 * @deprecated CDI eyes only
	 */
	protected AnamneseController() {
		this(null, null, null);
	}
	
	@Inject
	public AnamneseController(Result result, HttpServletRequest req, AnamneseLogic logic) {
		this.result = result;
		this.req = req;
		this.logic = logic;
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path({"", "/"})
	public void anamneseList() {
		result.use(Results.json()).from(logic.listAll(), "anamneseList").recursive().serialize();
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/{anamnese.id}")
	public void anamneseUnique(Anamnese anamnese) {
		result.use(Results.json()).from(logic.load(anamnese.getId()), "anamnese").recursive().serialize();
	}

	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/add")
	public void add(Anamnese anamnese) {
		
		Anamnese existe = logic.existe(anamnese);
		
		if (existe == null){
			logic.persist(anamnese);
			
			System.out.println("sign in success!");
			this.result.use(Results.http()).setStatusCode(200);
		}else{
			this.result.use(Results.http()).setStatusCode(401);
		}
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/remove")
	public void remove(Long id) {
		Anamnese remove = new Anamnese();
		remove.setId(id);
		this.logic.remove(remove);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path({"", "/"})
	public void edit(Anamnese anamnese) {
		this.logic.update(anamnese);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
}