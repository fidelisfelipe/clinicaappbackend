package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.dao.impl.DefaultExameDao;
import br.com.caelum.vraptor.backend.model.Exame;

public class ExameLogic {
	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	private DefaultExameDao exames;
	
	protected ExameLogic() {
	}

	@Inject
	public ExameLogic(DefaultExameDao exames){
		this.exames = exames;
	}
	
	public Exame load(long id) {
		return exames.load(id);
	}
	
	public void update(Exame exame) {
		exames.update(exame);
	}

	public void persist(Exame exame) {
		exames.persist(exame);
	}

	public void delete(Exame exame) {
		exames.delete(exame);
	}

	public List<Exame> listAll() {
		return exames.listAll();
	}
	
	public Exame existe(Exame exame){
		return exames.existe(exame);
	}
	
	public void remove(Exame exame) {
		exames.delete(exame);
	}

	public void refresh(Exame exame) {
		exames.refresh(exame);
	}

	public void verificarDadosOrigatoriosDefault(Exame exame) throws NegocioException {
		if(exame.getNome()== null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
	}
}
