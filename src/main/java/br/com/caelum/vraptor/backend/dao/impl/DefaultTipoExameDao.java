package br.com.caelum.vraptor.backend.dao.impl;


import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.model.TipoExame;
import br.com.caelum.vraptor.backend.util.PreconditionUtil;

public class DefaultTipoExameDao extends DefaultGenericDao<TipoExame> {
	
	/**
	 * @deprecated CDI eyes only
	 */	
	protected DefaultTipoExameDao() {
		this(null);
	}
	
	@Inject
	public DefaultTipoExameDao(Session session) {
		super(session);
	}
	
	/**
	 * Verifica se existe o tipoExame informado pelo nome
	 * 
	 * @param tipoExame
	 * @return
	 */
	public TipoExame existe(TipoExame tipoExame) {
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(tipoExame);
		return (TipoExame) getSession().createCriteria(TipoExame.class)
				.add(Restrictions.eq("nome", tipoExame.getNome())).uniqueResult();
	}
	
}
