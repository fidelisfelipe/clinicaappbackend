package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.dao.impl.DefaultAnamneseDao;
import br.com.caelum.vraptor.backend.dao.impl.DefaultExameDao;
import br.com.caelum.vraptor.backend.model.Anamnese;
import br.com.caelum.vraptor.backend.model.Exame;
import br.com.caelum.vraptor.backend.model.TipoExame;

public class AnamneseLogic {
	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	private DefaultAnamneseDao anamneses;
	
	protected AnamneseLogic() {
	}

	@Inject
	public AnamneseLogic(DefaultAnamneseDao anamneses){
		this.anamneses = anamneses;
	}
	
	public Anamnese load(long id) {
		return anamneses.load(id);
	}
	
	public void update(Anamnese anamnese) {
		anamneses.update(anamnese);
	}

	public void persist(Anamnese anamnese) {
		anamneses.persist(anamnese);
	}

	public void delete(Anamnese anamnese) {
		anamneses.delete(anamnese);
	}

	public List<Anamnese> listAll() {
		return anamneses.listAll();
	}
	
	public Anamnese existe(Anamnese object){
		return anamneses.existe(object);
	}
	
	public void remove(Anamnese object) {
		anamneses.delete(object);
	}

	public void refresh(Anamnese object) {
		anamneses.refresh(object);
	}

	public void verificarDadosOrigatoriosDefault(Anamnese object) throws NegocioException {
		//not implmented
	}
}
