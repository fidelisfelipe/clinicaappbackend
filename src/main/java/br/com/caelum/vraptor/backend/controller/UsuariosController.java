package br.com.caelum.vraptor.backend.controller;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.backend.business.UsuariosLogic;
import br.com.caelum.vraptor.backend.model.Usuario;
import br.com.caelum.vraptor.backend.model.UsuarioWeb;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
@Path("/usuarios")
@Controller
public class UsuariosController {

	private final Result result;
	private final UsuariosLogic logic;
	private final ServletContext contexto;
	private final ServletRequest req;
	private final UsuarioWeb usuarioWeb;
	private Validator validator;
	/**
	 * @deprecated CDI eyes only
	 */
	protected UsuariosController() {
		this(null, null, null, null, null, null);
	}
	
	@Inject
	public UsuariosController(Result result, UsuariosLogic logic, UsuarioWeb usuarioWeb, ServletContext contexto, ServletRequest req, Validator validator) {
		this.result = result;
		this.logic = logic;
		this.usuarioWeb = usuarioWeb;
		this.contexto = contexto;
		this.req = req;
		this.validator = validator;
	}
	@Get
	@Path({"","/"})
	public void index() {
		result.include("usuarioList", logic.listAll());
	}
	@Delete
	@Path({"/remove/{usuario.id}"})
	public void remove(Usuario usuario) {
		result.on(HibernateException.class).forwardTo(this).fail();
		logic.delete(usuario);
		result.forwardTo(this).index();
	}
	@Consumes("application/json")
	@Post
	@Path("/novo")
	public void index(Usuario usuario){
		logic.persist(usuario);
		this.result.use(Results.http()).setStatusCode(200);
	}
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/login")
	public void login(Usuario usuarioWeb) {
		if(usuarioWeb != null 
				&& usuarioWeb.getEmail() != null
				&& usuarioWeb.getSenha() != null){
		//TODO: logic login not implemented
			Usuario existe = logic.existe(usuarioWeb);
			if (existe != null){
				System.out.println("login success!");
				existe.setAuthorized(true);
				this.result.use(Results.json()).from(existe, "userCurrent").serialize();
			}else{
				this.result.use(Results.http()).setStatusCode(401);
				
			}
		}else{
			this.result.use(Results.http()).setStatusCode(401);
		}
		
		
	}
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/signin")
	public void signin(@Valid Usuario usuarioWeb) {
		validator.validate(usuarioWeb);
		validator.onErrorSendBadRequest();
		
		if(usuarioWeb != null 
				&& usuarioWeb.getEmail() != null
				&& usuarioWeb.getSenha() != null){
		//TODO: logic login not implemented
			Usuario existe = logic.existe(usuarioWeb);
			if (existe == null){
				usuarioWeb.setAtivo(true);//TODO: criar tela para autorização
				logic.persist(usuarioWeb);
				
				System.out.println("sign in success!");
				this.result.use(Results.json()).from(usuarioWeb, "userCurrent").serialize();
			}else{
				this.result.use(Results.http()).setStatusCode(401);
			}
		}
		
		
	}
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/logout")
	public void logout(String token) {
		//TODO: logic logout not implemented
		if(token != null)
			this.result.use(Results.json()).from(new Usuario()).serialize();
	}
	private void signValidation(){
		validator.onErrorSendBadRequest();
	}
	private void fail(){
		result.use(Results.json()).withoutRoot().from("fail").serialize();
		result.nothing();
	}
	@Get
	@Path("/{id}")
	public void index(Integer id){
	}
	
	@Get
	@Path("/json")
	public void listUsers(){
		result.use(Results.json()).withoutRoot().from(logic.listAll()).serialize();
	}
}