package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.dao.impl.DefaultConsultaDao;
import br.com.caelum.vraptor.backend.model.Consulta;

public class ConsultaLogic {
	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	private static final String REGISTRO_JA_EXISTE = "Registro já exista para esta data";
	private DefaultConsultaDao consultas;
	
	protected ConsultaLogic() {
	}

	@Inject
	public ConsultaLogic(DefaultConsultaDao consultas){
		this.consultas = consultas;
	}
	
	public Consulta load(long id) {
		return consultas.load(id);
	}
	
	public void update(Consulta consulta) {
		consultas.update(consulta);
	}

	public void persist(Consulta consulta) throws NegocioException {
		if(existe(consulta) != null){
			throw new NegocioException(REGISTRO_JA_EXISTE);
		}
		consultas.persist(consulta);
	}

	public void delete(Consulta consulta) {
		consultas.delete(consulta);
	}

	public List<Consulta> listAll() {
		return consultas.listAll();
	}
	
	public Consulta existe(Consulta consulta){
		return consultas.existe(consulta);
	}
	
	public void remove(Consulta consulta) {
		consultas.delete(consulta);
	}

	public void refresh(Consulta consulta) {
		consultas.refresh(consulta);
	}

	public void verificarDadosOrigatoriosDefault(Consulta consulta) throws NegocioException {
		//not implemented requirements
	}
}
