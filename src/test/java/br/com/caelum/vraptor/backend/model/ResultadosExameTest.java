package br.com.caelum.vraptor.backend.model;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.backend.GenericTest;
/**
 * @author fidelis.guimaraeso
 *
 */
public class ResultadosExameTest extends GenericTest {

	@Before
	public void setUp(){
	}

	/**
	 * Cria dois Resultados de Exame para um paciente
	 */
	@Test
	public void testCriaDoisResultadosExameParaUmPaciente(){
		String nomeTipoExame = "HEMOGRAMA";

		//para o uma bateria de Hemogramas
		TipoExame tipo = criaTipoExamePorNome(nomeTipoExame);

		//o exame de HEMÁCEAS
		String nomeExame = "HEMÁCEAS";
		String siglaExame = "HEM";
		Exame exame1 = criarExamePorNome(nomeExame, siglaExame);
		exame1.setTipo(tipo);
		
		//tem um valor
		ResultadoExame result1 = new ResultadoExame();
		result1.setValor("1.0");
		result1.setExame(exame1);
		
		//o exame de LEOCÓCITOS
		nomeExame = "LEOCÓCITOS";
		siglaExame = "LEUC";
		Exame exame2 = criarExamePorNome(nomeExame, siglaExame);
		exame2.setTipo(tipo);
		
		//tem um valor
		ResultadoExame result2 = new ResultadoExame();
		result2.setValor("2.0");
		result2.setExame(exame2);
		
		//o paciente que fez esse exame
		Paciente paciente = criarPacientePorNome("Bob");
		
		//possui alguns resultados
		List<ResultadoExame> resultados = new ArrayList<ResultadoExame>();
		resultados.add(result1);
		resultados.add(result2);
		
		paciente.setResultadoList(resultados);
		
		for (ResultadoExame result : resultados) {
			assertTrue("deve conter resultados do tipo: "+tipo.getNome(), result.getExame().getTipo().equals(tipo));
		}
		assertTrue("deve conter 2 tipos.", resultados.size() == 2);
	}
	
	/**
	 * Recupera dados para criação de tabela de um tipo de exame
	 */
	@Test
	public void testCriaTabelaParaExibicao(){
		//data atual
		Date data = Calendar.getInstance().getTime();
		
		String nomeTipoExame = "HEMOGRAMA";

		//para o uma bateria de Hemogramas
		TipoExame tipo = criaTipoExamePorNome(nomeTipoExame);

		//o exame de HEMÁCEAS
		String nomeExame = "HEMÁCEAS";
		String siglaExame = "HEM";
		Exame exame1 = criarExamePorNome(nomeExame, siglaExame);
		exame1.setTipo(tipo);

		//tem um valor
		ResultadoExame result1 = new ResultadoExame();
		result1.setValor("1.0");
		result1.setExame(exame1);
		result1.setData(data);

		//o exame de LEOCÓCITOS
		nomeExame = "LEOCÓCITOS";
		siglaExame = "LEUC";
		Exame exame2 = criarExamePorNome(nomeExame, siglaExame);
		exame2.setTipo(tipo);

		//tem um valor
		ResultadoExame result2 = new ResultadoExame();
		result2.setValor("2.0");
		result2.setExame(exame2);
		result2.setData(data);

		//o paciente que fez esse exame
		Paciente paciente = criarPacientePorNome("Bob");

		//possui alguns resultados
		List<ResultadoExame> resultados = new ArrayList<ResultadoExame>();
		resultados.add(result1);
		resultados.add(result2);

		paciente.setResultadoList(resultados);

		//lista de siglas não duplicadas
		Set<String> siglaList = new HashSet<String>();
		for (ResultadoExame result : paciente.getResultadoList()) {
			siglaList.add(result.getExame().getSigla());
		}
		assertTrue("deve ter duas siglas", siglaList.size() == 2);

		//lista de datas não duplicadas
		Set<Date> dateList = new HashSet<Date>();
		for (ResultadoExame result : paciente.getResultadoList()) {
				dateList.add(result.getData());
		}
		assertTrue("deve ter uma data", dateList.size() == 1);

	}
	
	private Paciente criarPacientePorNome(String nome) {
		Paciente paciente = new Paciente();
		paciente.setId(1l);
		paciente.setNome(nome);
		return paciente;
	}

	private Exame criarExamePorNome(String nome, String sigla) {
		Exame exame = new Exame();
		exame.setId(1l);
		exame.setNome(nome);
		exame.setSigla(sigla);
		return exame;
	}

	private TipoExame criaTipoExamePorNome(String nome) {
		TipoExame tipo = new TipoExame();
		tipo.setId(1l);
		tipo.setNome(nome);
		return tipo;
	}
		
}
