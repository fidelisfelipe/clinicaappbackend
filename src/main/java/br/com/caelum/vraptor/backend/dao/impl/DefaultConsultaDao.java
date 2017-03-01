package br.com.caelum.vraptor.backend.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.model.Consulta;
import br.com.caelum.vraptor.backend.model.Paciente;
import br.com.caelum.vraptor.backend.util.PreconditionUtil;

/**
 * @author Fidelis.Guimaraes
 *
 */
public class DefaultConsultaDao extends DefaultGenericDao<Consulta> {
	
	/**
	 * @deprecated CDI eyes only
	 */	
	protected DefaultConsultaDao() {
		this(null);
	}
	
	@Inject
	public DefaultConsultaDao(Session session) {
		super(session);
	}
	
	/**
	 * Verifica se existe a consulta informado pelo Paciente
	 * 
	 * @param consulta
	 * @return
	 */
	public Consulta existe(Consulta consulta) {
		//not implements exists 
		return null;
	}

}