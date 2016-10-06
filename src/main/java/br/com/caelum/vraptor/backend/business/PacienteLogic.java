package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.dao.impl.DefaultPacienteDao;
import br.com.caelum.vraptor.backend.model.Paciente;

/**
 * @author Fidelis.Guimaraes
 *
 */
public class PacienteLogic {

	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	private DefaultPacienteDao pacientes;
	
	protected PacienteLogic() {
	}

	@Inject
	public PacienteLogic(DefaultPacienteDao pacientes){
		this.pacientes = pacientes;
	}
	
	public Paciente load(long id) {
		return pacientes.load(id);
	}
	
	public void update(Paciente paciente) {
		pacientes.update(paciente);
	}

	public void persist(Paciente paciente) {
		pacientes.persist(paciente);
	}

	public void delete(Paciente paciente) {
		pacientes.delete(paciente);
	}

	public List<Paciente> listAll() {
		return pacientes.listAll();
	}
	
	public Paciente existe(Paciente paciente){
		return pacientes.existe(paciente);
	}
	
	public void remove(Paciente paciente) {
		pacientes.delete(paciente);
	}

	public void refresh(Paciente paciente) {
		pacientes.refresh(paciente);
	}

	public void verificarDadosOrigatoriosDefault(Paciente paciente) throws NegocioException {
		if(paciente.getCpf() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
	}
}
