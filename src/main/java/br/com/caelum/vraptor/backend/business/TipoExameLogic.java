package br.com.caelum.vraptor.backend.business;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.dao.impl.DefaultTipoExameDao;
import br.com.caelum.vraptor.backend.model.TipoExame;

public class TipoExameLogic {
	

		private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
		private DefaultTipoExameDao tipoExames;
		
		protected TipoExameLogic() {
		}

		@Inject
		public TipoExameLogic(DefaultTipoExameDao tipoExames){
			this.tipoExames = tipoExames;
		}
		
		public TipoExame load(long id) {
			return tipoExames.load(id);
		}
		
		public void update(TipoExame tipoExame) {
			tipoExames.update(tipoExame);
		}

		public void persist(TipoExame tipoExame) {
			tipoExames.persist(tipoExame);
		}

		public void delete(TipoExame tipoExame) {
			tipoExames.delete(tipoExame);
		}

		public List<TipoExame> listAll() {
			return tipoExames.listAll();
		}
		
		public TipoExame existe(TipoExame tipoExame){
			return tipoExames.existe(tipoExame);
		}
		
		public void remove(TipoExame tipoExame) {
			tipoExames.delete(tipoExame);
		}

		public void refresh(TipoExame tipoExame) {
			tipoExames.refresh(tipoExame);
		}

		public void verificarDadosOrigatoriosDefault(TipoExame tipoExame) throws NegocioException {
			if(tipoExame.getNome()== null){
				throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
			}
		}
}
