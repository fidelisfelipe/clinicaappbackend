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
import br.com.caelum.vraptor.backend.business.PacienteLogic;
import br.com.caelum.vraptor.backend.model.Paciente;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;

/**
 * @author fidelis.guimaraes
 *
 */
@Path("/pacientes")
@Controller
public class PacientesController {

	private final Result result;
	private HttpServletRequest req;
	@Inject
	private ServletContext context;
	@Inject
	private Router router;
	@Inject
	private PacienteLogic logic;
	/**
	 * @deprecated CDI eyes only
	 */
	protected PacientesController() {
		this(null, null, null);
	}
	
	@Inject
	public PacientesController(Result result, HttpServletRequest req, PacienteLogic logic) {
		this.result = result;
		this.req = req;
		this.logic = logic;
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path({"", "/"})
	public void pacienteList() {
		result.use(Results.json()).from(logic.listAll(), "pacienteList").serialize();
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/{paciente.id}")
	public void pacienteUnique(Paciente paciente) {
		result.use(Results.json()).from(logic.load(paciente.getId()), "paciente").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/add")
	public void add(Paciente paciente) {
		this.logic.persist(paciente);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/remove")
	public void remove(Paciente paciente) {
		this.logic.remove(paciente);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path({"", "/"})
	public void edit(Paciente paciente) {
		this.logic.update(paciente);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
}