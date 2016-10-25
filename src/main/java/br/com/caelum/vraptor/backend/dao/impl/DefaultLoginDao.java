package br.com.caelum.vraptor.backend.dao.impl;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.dao.LoginDao;
import br.com.caelum.vraptor.backend.model.Login;
import br.com.caelum.vraptor.backend.model.Usuario;
import br.com.caelum.vraptor.backend.util.PreconditionUtil;

/**
 * @author fidelis.guimaraes
 *
 */
public class DefaultLoginDao extends DefaultGenericDao<Login> implements
	LoginDao {
	
	public DefaultLoginDao() {
		this(null);
	}
	
	@Inject
	protected DefaultLoginDao(Session session) {
		super(session);
	}

	/**
	 * Verifica se existe o usuário informado e ativo
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario existe(Usuario usuario) {
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(usuario);
		return (Usuario) getSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("username", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getSenha()))
				.add(Restrictions.eq("ativo", true)).uniqueResult();
	}
}