package br.com.caelum.vraptor.backend.dao.impl;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.model.Exame;
import br.com.caelum.vraptor.backend.util.PreconditionUtil;

public class DefaultExameDao extends DefaultGenericDao<Exame> {
	
	/**
	 * @deprecated CDI eyes only
	 */	
	protected DefaultExameDao() {
		this(null);
	}
	
	@Inject
	public DefaultExameDao(Session session) {
		super(session);
	}
	
	/**
	 * Verifica se existe o exame informado pelo nome
	 * 
	 * @param exame
	 * @return
	 */
	public Exame existe(Exame exame) {
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(exame);
		return (Exame) getSession().createCriteria(Exame.class)
				.add(Restrictions.eq("nome", exame.getNome())).uniqueResult();
	}
	
}