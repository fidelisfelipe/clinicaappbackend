package br.com.caelum.vraptor.backend.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.model.Exame;
import br.com.caelum.vraptor.backend.model.TipoExame;
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
	/**
	 * Verifica se existe o exame informado pelo nome
	 * 
	 * @param exame
	 * @return
	 */
	public List<Exame> por(TipoExame tipo) {
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(tipo);
		return (List<Exame>)getSession().createCriteria(Exame.class)
				.add(Restrictions.eq("tipo.id", tipo.getId())).list();
	}

	public List<Exame> listAll(TipoExame tipoExame) {
		return (List<Exame>)getSession().createCriteria(Exame.class)
				.add(Restrictions.eq("tipo.id", tipoExame.getId())).list();
	}
	
}