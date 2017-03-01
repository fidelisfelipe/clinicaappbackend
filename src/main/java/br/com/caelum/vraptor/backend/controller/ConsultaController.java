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
import br.com.caelum.vraptor.backend.business.AnamneseLogic;
import br.com.caelum.vraptor.backend.business.ConsultaLogic;
import br.com.caelum.vraptor.backend.business.PacienteLogic;
import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.model.Anamnese;
import br.com.caelum.vraptor.backend.model.Consulta;
import br.com.caelum.vraptor.backend.model.Paciente;
import br.com.caelum.vraptor.view.Results;

/**
 * @author fidelis.guimaraes
 *
 */
@Path("/consultas")
@Controller
public class ConsultaController {

	private Result result;
	private ConsultaLogic logic;
	private PacienteLogic logicPaciente;
	private AnamneseLogic logicAnamnese;

	protected ConsultaController() {
	}
	
	@Inject
	public ConsultaController (Result result, ConsultaLogic logic, PacienteLogic logicPaciente, AnamneseLogic logicAnamnese){
		this.result = result;
		this.logic = logic;
		this.logicPaciente = logicPaciente;
		this.logicAnamnese = logicAnamnese;
	}
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/add")
	public void consultaAdd(Consulta consulta, Long pacienteId){
		try {
			Anamnese anamnese = new Anamnese();
			this.logicAnamnese.persist(anamnese);
			consulta.setAnamnese(anamnese);
			this.logic.persist(consulta);
			Paciente paciente = logicPaciente.load(pacienteId);
			paciente.getConsultaList().add(consulta);
			this.logicPaciente.update(paciente);
			this.result.use(Results.json()).from("OK").serialize();
		} catch (NegocioException  e) {
			this.result.use(Results.http()).setStatusCode(409);
		}
		
	}
	@Consumes("application/json")
	@Get
	@Path({"", "/"})
	public void consultaList() {
		this.result.use(Results.json()).from(this.logic.listAll(), "consultaList").serialize();
	}
	@Consumes("application/json")
	@Get
	@Path("/{consulta.id}")
	public void consultaLoad(Consulta consulta) {
		this.result.use(Results.json()).from(this.logic.load(consulta.getId()), "consulta").recursive().serialize();
	}
	@Consumes("application/json")
	@Get
	@Path("/por/paciente/{paciente.id}")
	public void consultaListPorPaciente(Paciente paciente) {
		paciente = this.logicPaciente.load(paciente.getId());
		this.result.use(Results.json()).from(paciente.getConsultaList(), "consultaList").serialize();
	}
	@Consumes("application/json")
	@Put
	@Path("/{consulta.id}")
	public void consultaUpdate(Consulta consulta) {
		this.logic.update(consulta);
		this.result.use(Results.json()).from("OK").serialize();
	}
	@Transactional
	@Consumes("application/json")
	@Delete
	@Path("/{consulta.id}")
	public void consultaRemove(Consulta consulta) {
		this.logic.delete(consulta);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
}
