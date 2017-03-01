package br.com.caelum.vraptor.backend.dao.impl;

import javax.inject.Inject;

import org.hibernate.Session;

import br.com.caelum.vraptor.backend.model.Anamnese;

public class DefaultAnamneseDao extends DefaultGenericDao<Anamnese> {
	
	/**
	 * @deprecated CDI eyes only
	 */	
	protected DefaultAnamneseDao() {
		this(null);
	}
	
	@Inject
	public DefaultAnamneseDao(Session session) {
		super(session);
	}

	public Anamnese existe(Anamnese anamnese) {
		//not implemented
		return null;
	}
	
}