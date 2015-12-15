package es.us.agoraus.counting.algorithms;

import java.util.List;

import org.junit.Test;

import es.us.agoraus.counting.domain.Resultado;

public class AlgorithmTest {

	@Test
	public void testPredefinedCountAnswerZeroNo() throws Exception {
		
		List<Resultado> resultados;
		
		resultados = es.us.agoraus.counting.algorithms.Test.naturalCountingAlgorithmTestVotation();
		
		assert !resultados.isEmpty();
		
		assert resultados.get(0).getNumeroNo() == 0;
		
	}
	
	@Test
	public void testPredefinedCountAnswerZeroYes() throws Exception {
		
		List<Resultado> resultados;
		
		resultados = es.us.agoraus.counting.algorithms.Test.naturalCountingAlgorithmTestVotation();
		
		assert !resultados.isEmpty();
		
		assert resultados.get(0).getNumeroSi() == 2;
		
	}
	
	@Test
	public void testPredefinedCountAnswerOneNo() throws Exception {
		
		List<Resultado> resultados;
		
		resultados = es.us.agoraus.counting.algorithms.Test.naturalCountingAlgorithmTestVotation();
		
		assert !resultados.isEmpty();
		
		assert resultados.get(1).getNumeroNo() == 1;
		
	}
	
	@Test
	public void testPredefinedCountAnswerOneYes() throws Exception {
		
		List<Resultado> resultados;
		
		resultados = es.us.agoraus.counting.algorithms.Test.naturalCountingAlgorithmTestVotation();
		
		assert !resultados.isEmpty();
		
		assert resultados.get(1).getNumeroSi() == 1;
		
	}
	
}
