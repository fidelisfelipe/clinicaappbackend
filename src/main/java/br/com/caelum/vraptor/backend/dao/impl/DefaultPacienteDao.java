package br.com.caelum.vraptor.backend.dao.impl;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.model.Paciente;
import br.com.caelum.vraptor.backend.model.ResultadoExame;
import br.com.caelum.vraptor.backend.util.PreconditionUtil;

/**
 * @author Fidelis.Guimaraes
 *
 */
public class DefaultPacienteDao extends DefaultGenericDao<Paciente> {
	
	/**
	 * @deprecated CDI eyes only
	 */	
	protected DefaultPacienteDao() {
		this(null);
	}
	
	@Inject
	public DefaultPacienteDao(Session session) {
		super(session);
	}
	
	/**
	 * Verifica se existe o paciente informado pelo CPF
	 * 
	 * @param paciente
	 * @return
	 */
	public Paciente existe(Paciente paciente) {
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(paciente);
		return (Paciente) getSession().createCriteria(Paciente.class)
				.add(Restrictions.eq("cpf", paciente.getCpf())).uniqueResult();
	}

}