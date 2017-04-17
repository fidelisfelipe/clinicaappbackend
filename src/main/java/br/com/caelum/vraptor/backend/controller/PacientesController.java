package br.com.caelum.vraptor.backend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
import br.com.caelum.vraptor.backend.business.PacienteLogic;
import br.com.caelum.vraptor.backend.business.ResultadoExameLogic;
import br.com.caelum.vraptor.backend.business.TipoExameLogic;
import br.com.caelum.vraptor.backend.model.Paciente;
import br.com.caelum.vraptor.backend.model.ResultadoExame;
import br.com.caelum.vraptor.backend.model.TipoExame;
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
	@Inject
	private TipoExameLogic logicTipoExame;
	@Inject
	private ExameLogic logicExame;
	@Inject
	private ResultadoExameLogic logicResultadoExame;

	/**
	 * @deprecated CDI eyes only
	 */
	protected PacientesController() {
		this(null, null, null, null, null, null);
	}

	@Inject
	public PacientesController(Result result, HttpServletRequest req,
			PacienteLogic logic, TipoExameLogic logicTipoExame,
			ExameLogic logicExame, ResultadoExameLogic logicResultadoExame) {
		this.result = result;
		this.req = req;
		this.logic = logic;
		this.logicTipoExame = logicTipoExame;
		this.logicExame = logicExame;
		this.logicResultadoExame = logicResultadoExame;
	}

	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path({ "", "/" })
	public void pacienteList() {
		result.use(Results.json()).from(logic.listAll(), "pacienteList")
				.serialize();
	}

	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/{paciente.id}")
	public void pacienteUnique(Paciente paciente) {
		result.use(Results.json())
				.from(logic.load(paciente.getId()), "paciente").serialize();
	}

	@Consumes(value = "application/json")
	@Post
	@Path("/exameList/por/paciente/por/tipoExame")
	public void tipoExameListPorPaciente(Long pacienteId, Long tipoExameId) {
		// retorna os exames do paciente
		List<ResultadoExame> list = logic.load(pacienteId).getResultadoList();
		result.use(Results.json()).from(list, "resultadoExameList").serialize();
	}

	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/tipoExameList")
	public void exameList(Paciente paciente) {
		// retorna os exames do paciente
		// logic.load(paciente.getId()).getResultadoList()
		result.use(Results.json())
				.from(logicTipoExame.listAll(), "tipoExameList").serialize();
	}
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/tipoExameList/por/paciente/{paciente.id}")
	public void tipoExameListPorPaciente(Paciente paciente) {
		// retorna os exames do paciente
		// logic.load(paciente.getId()).getResultadoList()
		
		List<ResultadoExame> resultadoList = new ArrayList<ResultadoExame>(); 
		paciente = logic.load(paciente.getId());
		
		if(paciente.getResultadoList() != null)
		resultadoList.addAll(paciente.getResultadoList());
		
		List<TipoExame> tipoExameList = new ArrayList<TipoExame>();
		
		for (ResultadoExame result : resultadoList) {
			tipoExameList.add(result.getExame().getTipo());
		}
		
		result.use(Results.json()).from(tipoExameList, "tipoExameList").serialize();
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/consultaList")
	public void consultaList(Paciente paciente) {
		result.use(Results.json())
				.from(logic.load(paciente.getId()).getConsultaList(), "consultaList").serialize();
	}

	@Consumes(value = "application/json")
	@Post
	@Path("/resultados/total")
	public void exameListTotal(Long pacienteId) {
		Integer resultadoExameListTotal = logic.load(pacienteId)
				.getResultadoList().size();
		result.use(Results.json())
				.from(resultadoExameListTotal, "resultadoExameListTotal")
				.serialize();
	}

	@Consumes(value = "application/json")
	@Post
	@Path("/resultados/por/tipoExame")
	public void exameList(Long pacienteId, Long tipoExameId) {
		// retorna os exames do paciente
		// logic.load(paciente.getId()).getResultadoList()
		Paciente paciente = logic.load(pacienteId);
		List<ResultadoExame> geral = paciente.getResultadoList() != null 
				&& !paciente.getResultadoList().isEmpty() ? 
						paciente.getResultadoList() : Collections.EMPTY_LIST;
		TipoExame tipo = logicTipoExame.load(tipoExameId);
		List<ResultadoExame> resultadoExameList = new ArrayList<ResultadoExame>();
		for (ResultadoExame resultado : geral) {
			if (resultado.getExame() != null
					&& resultado.getExame().getTipo() != null
					&& resultado.getExame().getTipo().equals(tipo)) {
				resultadoExameList.add(resultado);
			}
		}
		orderByDate(resultadoExameList);
		
		result.use(Results.json())
				.from(resultadoExameList, "resultadoExameList").recursive()
				.serialize();
	}
	private void orderByDate(List<ResultadoExame> resultadoExameList) {
		Collections.sort( resultadoExameList , new Comparator<ResultadoExame>() {    
		     public int compare(ResultadoExame o1, ResultadoExame o2) {    
		         return o1.getData().compareTo(o2.getData());    
		        }    
		    }  
		);
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/resultado/update")
	
	public void resultadoUpdate(ResultadoExame resultado, Paciente paciente) {
		// load paciente
		paciente = logic.load(paciente.getId());
		logicResultadoExame.update(resultado);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/resultado/add")
	public void resultadoAdd(ResultadoExame resultado, Paciente paciente, boolean clearValue) {
		// load paciente
		paciente = logic.load(paciente.getId());
		List<ResultadoExame> list = paciente.getResultadoList();
		boolean exist = false;

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		ResultadoExame remove = null;
		for (ResultadoExame resultadoExame : list) {
			
			
			boolean existeData = resultadoExame.getData().equals(resultado.getData());
			boolean existeExame = resultadoExame.getExame().getId() == resultado.getExame().getId();
			
			
			boolean existeValor = resultadoExame.getValor().equals(resultado.getValor());
			
			if (existeValor && existeData && existeExame) {			
				//remove valor qnd a requisição solicita
				if(clearValue){
					remove = resultadoExame;
					paciente.getResultadoList().remove(remove);
					logicResultadoExame.delete(remove);
					logic.update(paciente);
					
					exist = true;
				}
				
				this.result.use(Results.json()).from("OK").serialize();
				break;
			}
		}
		
		//add novo
		if(!exist){
			// cast persistence
			resultado.setExame(logicExame.load(resultado.getExame().getId()));

			this.logicResultadoExame.persist(resultado);

			// set resultado
			paciente.getResultadoList().add(resultado);
			// vincula
			logic.update(paciente);
			this.result.use(Results.json()).from("OK").serialize();
		}

	}
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/resultado/remove")
	public void resultadoRemove(ResultadoExame resultado, Paciente paciente) {
		// cast for persistent bag
		resultado = logicResultadoExame.load(resultado.getId());
		paciente = logic.load(paciente.getId());
		paciente.getResultadoList().remove(resultado);
		logic.update(paciente);
		this.logicResultadoExame.remove(resultado);
		
		this.result.use(Results.json()).from("OK").serialize();
	}
	
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/resultado/remove/por/data")
	public void resultadoRemovePorData(Date data, Paciente paciente) {
		paciente = logic.load(paciente.getId());
		List<ResultadoExame> atualList = paciente.getResultadoList();
		List<ResultadoExame> removerList = new ArrayList<ResultadoExame>();
		for (ResultadoExame result : atualList) {
			if(result.getData().equals(data)){
				removerList.add(result);
			}
		}
		
		for (ResultadoExame resultado : removerList) {
			logicResultadoExame.delete(resultado);
		}
		
		for (ResultadoExame resultado : removerList) {
			logicResultadoExame.delete(resultado);
			atualList.remove(resultado);
		}
		
		logic.update(paciente);
		
		this.result.use(Results.json()).from("OK").serialize();
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
	public void remove(Long id) {
		Paciente remove = new Paciente();
		remove.setId(id);
		this.logic.remove(remove);
		this.result.use(Results.json()).from("OK").serialize();
	}

	@Transactional
	@Consumes("application/json")
	@Post
	@Path({ "", "/" })
	public void edit(Paciente paciente) {
		this.logic.update(paciente);
		result.use(Results.json())
				.from(logic.load(paciente.getId()), "paciente").serialize();
	}

}