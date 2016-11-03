package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.dao.impl.DefaultResultadoExameDao;
import br.com.caelum.vraptor.backend.model.ResultadoExame;

public class ResultadoExameLogic {
	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	private DefaultResultadoExameDao resultados;
	
	protected ResultadoExameLogic() {
	}

	@Inject
	public ResultadoExameLogic(DefaultResultadoExameDao resultados){
		this.resultados = resultados;
	}
	
	public ResultadoExame load(long id) {
		return resultados.load(id);
	}
	
	public void update(ResultadoExame resultado) {
		resultados.update(resultado);
	}

	public void persist(ResultadoExame resultado) {
		resultados.persist(resultado);
	}

	public void delete(ResultadoExame resultado) {
		resultados.delete(resultado);
	}

	public List<ResultadoExame> listAll() {
		return resultados.listAll();
	}
	
	public ResultadoExame existe(ResultadoExame resultado){
		return resultados.existe(resultado);
	}
	
	public void remove(ResultadoExame resultado) {
		resultados.delete(resultado);
	}

	public void refresh(ResultadoExame resultado) {
		resultados.refresh(resultado);
	}

	public void verificarDadosOrigatoriosDefault(ResultadoExame resultado) throws NegocioException {
		if(resultado.getData() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
	}
}
