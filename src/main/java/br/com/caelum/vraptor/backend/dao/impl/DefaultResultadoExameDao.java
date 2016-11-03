package br.com.caelum.vraptor.backend.dao.impl;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.model.ResultadoExame;
import br.com.caelum.vraptor.backend.util.PreconditionUtil;

public class DefaultResultadoExameDao extends DefaultGenericDao<ResultadoExame> {

	protected DefaultResultadoExameDao() {
		this(null);
	}
	@Inject
	public DefaultResultadoExameDao(Session session) {
		super(session);
	}
	/**
	 * Verifica se existe o resultado informado pela data, valor,tipo
	 * 
	 * @param resultado
	 * @return
	 */
	public ResultadoExame existe(ResultadoExame resultado) {
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(resultado);
		return (ResultadoExame) getSession().createCriteria(ResultadoExame.class)
				.add(Restrictions.eq("data", resultado.getData()))
				.add(Restrictions.eq("valor", resultado.getValor()))
				.add(Restrictions.eq("exame.tipo.nome", resultado.getExame().getNome())).uniqueResult();
	}
	

}
