package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.us.agoraus.counting.domain.ReferendumResult;
import es.us.agoraus.counting.domain.Result;

public class AlgorithmTest {

	@Test
	public void testReferendumCount() throws Exception {
		List<Result> resultados;

		// Some votes are initialized and added into a votes list
		String vote1 = "[99, 57, 102, 51, 51, 54, 98, 55, 49, 101, 56, 99, 57, 102, 102, 55, 99, 56, 57, 101, 56, 102, 99, 56, 98, 51, 102, 56, 50, 98, 101, 51, 100, 98, 101, 97, 48, 52, 97, 97, 63, 49, 49, 55, 50, 55, 49, 48, 57, 57, 57, 56, 56, 53, 54, 52, 48, 53, 49, 51, 55, 49, 55, 54, 49, 53, 55, 57, 50, 54, 57, 49, 52, 56, 53, 49, 57, 49, 57, 48, 56, 53, 50, 52, 51, 54, 52, 52, 48, 56, 56, 57, 56, 57, 48, 53, 52, 51, 53, 53, 53, 50, 49, 51, 55, 55, 52, 50, 55, 49, 49, 57, 52, 55, 48, 55, 49, 54, 124, 49, 49, 54, 57, 53, 56, 51, 56, 53, 50, 57, 51, 53, 52, 49, 54, 55, 51, 48, 50, 50, 54, 49, 52, 55, 57, 51, 49, 55, 56, 50, 51, 52, 54, 53, 54, 56, 50, 49, 55, 57, 54, 50, 48, 53, 51, 57, 53, 52, 56, 51, 55, 48, 48, 48, 52, 51, 55, 56, 54, 56, 50, 56, 56, 52, 48, 53, 55, 56, 52, 49, 50, 52, 56, 49, 48, 53, 124, 49, 49, 55, 48, 52, 55, 48, 48, 54, 50, 49, 51, 56, 50, 48, 50, 52, 53, 51, 48, 56, 57, 56, 53, 52, 51, 50, 50, 50, 50, 48, 52, 57, 54, 49, 56, 50, 51, 48, 53, 50, 50, 57, 57, 50, 50, 54, 48, 54, 51, 56, 49, 56, 48, 52, 48, 51, 49, 48, 51, 49, 56, 48, 50, 57, 52, 57, 48, 54, 53, 50, 49, 54, 53, 54, 56, 52, 124, 49, 49, 53, 55, 48, 50, 57, 53, 57, 54, 48, 54, 56, 51, 50, 48, 54, 48, 57, 53, 51, 51, 50, 51, 56, 53, 54, 51, 52, 52, 55, 57, 48, 54, 49, 51, 50, 52, 50, 56, 57, 48, 49, 49, 51, 48, 49, 56, 49, 52, 49, 56, 48, 49, 52, 56, 49, 52, 51, 52, 51, 55, 48, 57, 55, 54, 53, 57, 50, 55, 55, 56, 51, 50, 53, 55, 51, 124, 49, 49, 54, 56, 53, 49, 57, 53, 54, 56, 54, 48, 48, 50, 51, 52, 57, 55, 54, 50, 51, 57, 49, 53, 53, 48, 49, 50, 54, 52, 57, 56, 53, 57, 56, 50, 52, 48, 50, 53, 55, 50, 54, 50, 53, 49, 57, 49, 52, 52, 56, 49, 55, 53, 51, 51, 55, 55, 57, 52, 52, 56, 50, 53, 55, 50, 57, 54, 49, 48, 53, 55, 50, 55, 48, 57, 54, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 51, 54, 55, 56, 56, 56, 57, 55, 50, 55, 57, 55, 53, 56, 56, 48, 57, 53, 55, 51, 51, 49, 54, 48, 56, 52, 52, 54, 56, 53, 48, 50, 52, 54, 48, 55, 54, 50, 49]";
		String vote2 = "[99, 57, 102, 51, 51, 54, 98, 55, 49, 101, 56, 99, 57, 102, 102, 55, 99, 56, 57, 101, 56, 102, 99, 56, 98, 51, 102, 56, 50, 98, 101, 51, 100, 98, 101, 97, 48, 52, 97, 97, 63, 49, 49, 55, 50, 55, 49, 48, 57, 57, 57, 56, 56, 53, 54, 52, 48, 53, 49, 51, 55, 49, 55, 54, 49, 53, 55, 57, 50, 54, 57, 49, 52, 56, 53, 49, 57, 49, 57, 48, 56, 53, 50, 52, 51, 54, 52, 52, 48, 56, 56, 57, 56, 57, 48, 53, 52, 51, 53, 53, 53, 50, 49, 51, 55, 55, 52, 50, 55, 49, 49, 57, 52, 55, 48, 55, 49, 54, 124, 49, 49, 54, 57, 53, 56, 51, 56, 53, 50, 57, 51, 53, 52, 49, 54, 55, 51, 48, 50, 50, 54, 49, 52, 55, 57, 51, 49, 55, 56, 50, 51, 52, 54, 53, 54, 56, 50, 49, 55, 57, 54, 50, 48, 53, 51, 57, 53, 52, 56, 51, 55, 48, 48, 48, 52, 51, 55, 56, 54, 56, 50, 56, 56, 52, 48, 53, 55, 56, 52, 49, 50, 52, 56, 49, 48, 53, 124, 49, 49, 55, 48, 52, 55, 48, 48, 54, 50, 49, 51, 56, 50, 48, 50, 52, 53, 51, 48, 56, 57, 56, 53, 52, 51, 50, 50, 50, 50, 48, 52, 57, 54, 49, 56, 50, 51, 48, 53, 50, 50, 57, 57, 50, 50, 54, 48, 54, 51, 56, 49, 56, 48, 52, 48, 51, 49, 48, 51, 49, 56, 48, 50, 57, 52, 57, 48, 54, 53, 50, 49, 54, 53, 54, 56, 52, 124, 49, 49, 53, 55, 48, 50, 57, 53, 57, 54, 48, 54, 56, 51, 50, 48, 54, 48, 57, 53, 51, 51, 50, 51, 56, 53, 54, 51, 52, 52, 55, 57, 48, 54, 49, 51, 50, 52, 50, 56, 57, 48, 49, 49, 51, 48, 49, 56, 49, 52, 49, 56, 48, 49, 52, 56, 49, 52, 51, 52, 51, 55, 48, 57, 55, 54, 53, 57, 50, 55, 55, 56, 51, 50, 53, 55, 51, 124, 49, 49, 54, 56, 53, 49, 57, 53, 54, 56, 54, 48, 48, 50, 51, 52, 57, 55, 54, 50, 51, 57, 49, 53, 53, 48, 49, 50, 54, 52, 57, 56, 53, 57, 56, 50, 52, 48, 50, 53, 55, 50, 54, 50, 53, 49, 57, 49, 52, 52, 56, 49, 55, 53, 51, 51, 55, 55, 57, 52, 52, 56, 50, 53, 55, 50, 57, 54, 49, 48, 53, 55, 50, 55, 48, 57, 54, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 51, 54, 55, 56, 56, 56, 57, 55, 50, 55, 57, 55, 53, 56, 56, 48, 57, 53, 55, 51, 51, 49, 54, 48, 56, 52, 52, 54, 56, 53, 48, 50, 52, 54, 48, 55, 54, 50, 49]";
		String vote3 = "[50, 54, 52, 101, 50, 54, 55, 54, 98, 52, 50, 56, 50, 56, 98, 55, 101, 54, 51, 98, 102, 49, 55, 97, 97, 102, 52, 56, 50, 99, 98, 50, 54, 99, 56, 101, 50, 100, 56, 102, 63, 49, 49, 55, 50, 55, 49, 48, 57, 57, 57, 56, 56, 53, 54, 52, 48, 53, 49, 51, 55, 49, 55, 54, 49, 53, 55, 57, 50, 54, 57, 49, 52, 56, 53, 49, 57, 49, 57, 48, 56, 53, 50, 52, 51, 54, 52, 52, 48, 56, 56, 57, 56, 57, 48, 53, 52, 51, 53, 53, 53, 50, 49, 51, 55, 55, 52, 50, 55, 49, 49, 57, 52, 55, 48, 55, 49, 54, 124, 49, 49, 54, 57, 53, 56, 51, 56, 53, 50, 57, 51, 53, 52, 49, 54, 55, 51, 48, 50, 50, 54, 49, 52, 55, 57, 51, 49, 55, 56, 50, 51, 52, 54, 53, 54, 56, 50, 49, 55, 57, 54, 50, 48, 53, 51, 57, 53, 52, 56, 51, 55, 48, 48, 48, 52, 51, 55, 56, 54, 56, 50, 56, 56, 52, 48, 53, 55, 56, 52, 49, 50, 52, 56, 49, 48, 53, 124, 49, 49, 55, 48, 52, 55, 48, 48, 54, 50, 49, 51, 56, 50, 48, 50, 52, 53, 51, 48, 56, 57, 56, 53, 52, 51, 50, 50, 50, 50, 48, 52, 57, 54, 49, 56, 50, 51, 48, 53, 50, 50, 57, 57, 49, 50, 53, 49, 50, 55, 49, 51, 56, 57, 57, 55, 49, 51, 55, 53, 53, 49, 57, 51, 53, 51, 49, 53, 54, 53, 57, 52, 51, 55, 54, 50, 48, 124, 49, 49, 53, 55, 48, 50, 57, 53, 57, 54, 48, 54, 56, 51, 50, 48, 54, 48, 57, 53, 51, 51, 50, 51, 56, 53, 54, 51, 52, 52, 55, 57, 48, 54, 49, 51, 50, 52, 50, 56, 57, 48, 49, 49, 51, 48, 49, 56, 49, 52, 49, 56, 48, 49, 52, 56, 49, 52, 51, 52, 51, 55, 48, 57, 55, 54, 53, 57, 50, 55, 55, 56, 51, 50, 53, 55, 51, 124, 49, 49, 54, 56, 53, 49, 57, 53, 54, 56, 54, 48, 48, 50, 51, 52, 57, 55, 54, 50, 51, 57, 49, 53, 53, 48, 49, 50, 54, 52, 57, 56, 53, 57, 56, 50, 52, 48, 50, 53, 55, 50, 54, 50, 53, 49, 57, 49, 52, 52, 56, 49, 55, 53, 51, 51, 55, 55, 57, 52, 52, 56, 50, 53, 55, 50, 57, 54, 49, 48, 53, 55, 50, 55, 48, 57, 54, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 51, 54, 55, 56, 56, 56, 57, 55, 50, 55, 57, 55, 53, 56, 56, 48, 57, 53, 55, 51, 51, 49, 54, 48, 56, 52, 52, 54, 56, 53, 48, 50, 52, 54, 48, 55, 54, 50, 49]";
		String vote4 = "[101, 98, 51, 102, 99, 57, 53, 99, 53, 97, 48, 52, 51, 53, 100, 54, 102, 49, 98, 55, 101, 56, 54, 50, 48, 55, 102, 48, 102, 99, 97, 54, 49, 51, 57, 101, 50, 54, 56, 99, 63, 49, 49, 55, 50, 55, 49, 48, 57, 57, 57, 56, 56, 53, 54, 52, 48, 53, 49, 51, 55, 49, 55, 54, 49, 53, 55, 57, 50, 54, 57, 49, 52, 56, 53, 49, 57, 49, 57, 48, 56, 53, 50, 52, 51, 54, 52, 52, 48, 56, 56, 57, 56, 57, 48, 53, 52, 51, 53, 53, 53, 50, 49, 51, 55, 55, 52, 50, 55, 49, 49, 57, 52, 55, 48, 55, 49, 54, 124, 49, 49, 54, 57, 53, 56, 51, 56, 53, 50, 57, 51, 53, 52, 49, 54, 55, 51, 48, 50, 52, 51, 49, 55, 55, 55, 56, 54, 53, 54, 57, 56, 48, 48, 57, 52, 50, 50, 55, 53, 52, 49, 51, 49, 57, 53, 51, 48, 49, 53, 52, 55, 52, 49, 54, 54, 56, 57, 55, 57, 57, 51, 57, 49, 57, 53, 49, 53, 56, 53, 49, 48, 50, 55, 51, 50, 51, 124, 49, 49, 55, 50, 48, 53, 48, 53, 52, 56, 53, 48, 56, 52, 49, 57, 49, 55, 51, 55, 51, 51, 50, 48, 52, 50, 51, 57, 57, 55, 55, 57, 51, 50, 51, 52, 54, 53, 50, 49, 56, 56, 49, 48, 49, 52, 51, 56, 54, 54, 48, 53, 56, 56, 52, 50, 48, 53, 50, 50, 50, 57, 56, 54, 54, 52, 53, 55, 51, 57, 56, 50, 50, 52, 55, 52, 53, 124, 49, 49, 55, 49, 55, 48, 55, 53, 50, 51, 55, 56, 52, 56, 56, 52, 53, 54, 53, 53, 55, 52, 51, 48, 54, 56, 55, 51, 56, 56, 54, 56, 54, 48, 49, 56, 56, 51, 56, 51, 51, 50, 56, 54, 57, 48, 53, 48, 56, 50, 56, 55, 54, 57, 53, 49, 49, 52, 52, 50, 50, 55, 51, 52, 50, 52, 54, 51, 51, 52, 56, 48, 52, 49, 56, 52, 49, 124, 49, 49, 54, 56, 49, 49, 55, 48, 53, 51, 57, 48, 50, 57, 51, 55, 54, 52, 48, 54, 48, 52, 54, 53, 57, 56, 56, 56, 53, 48, 48, 52, 52, 49, 57, 49, 57, 54, 49, 56, 49, 51, 55, 53, 53, 57, 56, 50, 57, 54, 54, 53, 49, 56, 48, 49, 55, 57, 53, 53, 55, 53, 50, 56, 55, 48, 49, 53, 53, 51, 49, 54, 48, 55, 49, 53, 54, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 51, 54, 55, 56, 56, 56, 57, 55, 50, 55, 54, 51, 49, 48, 52, 54, 57, 53, 54, 53, 52, 52, 55, 51, 49, 54, 50, 49, 48, 53, 55, 48, 53, 54, 52, 51, 51, 57, 55]";
		String vote5 = "[101, 98, 51, 102, 99, 57, 53, 99, 53, 97, 48, 52, 51, 53, 100, 54, 102, 49, 98, 55, 101, 56, 54, 50, 48, 55, 102, 48, 102, 99, 97, 54, 49, 51, 57, 101, 50, 54, 56, 99, 63, 49, 49, 55, 50, 55, 49, 48, 57, 57, 57, 56, 56, 53, 54, 52, 48, 53, 49, 51, 55, 49, 55, 54, 49, 53, 55, 57, 50, 54, 57, 49, 52, 56, 53, 49, 57, 49, 57, 48, 56, 53, 50, 52, 51, 54, 52, 52, 48, 56, 56, 57, 56, 57, 48, 53, 52, 51, 53, 53, 53, 50, 49, 51, 55, 55, 52, 50, 55, 49, 49, 57, 52, 55, 48, 55, 49, 54, 124, 49, 49, 54, 57, 53, 56, 51, 56, 53, 50, 57, 51, 53, 52, 49, 54, 55, 51, 48, 50, 52, 51, 49, 55, 55, 55, 56, 54, 53, 54, 57, 56, 48, 48, 57, 52, 50, 50, 55, 53, 52, 49, 51, 49, 57, 53, 51, 48, 49, 53, 52, 55, 52, 49, 54, 54, 56, 57, 55, 57, 57, 51, 57, 49, 57, 53, 49, 53, 56, 53, 49, 48, 50, 55, 51, 50, 51, 124, 49, 49, 55, 50, 48, 53, 48, 53, 52, 56, 53, 48, 56, 52, 49, 57, 49, 55, 51, 55, 51, 51, 50, 48, 52, 50, 51, 57, 57, 55, 55, 57, 51, 50, 51, 52, 54, 53, 50, 49, 56, 56, 49, 48, 49, 52, 51, 56, 54, 54, 48, 53, 56, 56, 52, 50, 48, 53, 50, 50, 50, 57, 56, 54, 54, 52, 53, 55, 51, 57, 56, 50, 50, 52, 55, 52, 53, 124, 49, 49, 55, 49, 55, 48, 55, 53, 50, 51, 55, 56, 52, 56, 56, 52, 53, 54, 53, 53, 55, 52, 51, 48, 54, 56, 55, 51, 56, 56, 54, 56, 54, 48, 49, 56, 56, 51, 56, 51, 51, 50, 56, 54, 57, 48, 53, 48, 56, 50, 56, 55, 54, 57, 53, 49, 49, 52, 52, 50, 50, 55, 51, 52, 50, 52, 54, 51, 51, 52, 56, 48, 52, 49, 56, 52, 49, 124, 49, 49, 54, 56, 49, 49, 55, 48, 53, 51, 57, 48, 50, 57, 51, 55, 54, 52, 48, 54, 48, 52, 54, 53, 57, 56, 56, 56, 53, 48, 48, 52, 52, 49, 57, 49, 57, 54, 49, 56, 49, 51, 55, 53, 53, 57, 56, 50, 57, 54, 54, 53, 49, 56, 48, 49, 55, 57, 53, 53, 55, 53, 50, 56, 55, 48, 49, 53, 53, 51, 49, 54, 48, 55, 49, 53, 54, 124, 49, 49, 53, 48, 57, 53, 53, 48, 53, 50, 52, 53, 48, 57, 50, 55, 54, 50, 51, 57, 49, 54, 57, 53, 49, 48, 50, 55, 54, 48, 49, 53, 48, 54, 49, 53, 50, 57, 51, 54, 55, 56, 56, 56, 57, 55, 50, 55, 54, 51, 49, 48, 52, 54, 57, 53, 54, 53, 52, 52, 55, 51, 49, 54, 50, 49, 48, 53, 55, 48, 53, 54, 52, 51, 51, 57, 55]";

		List<String> votes = new ArrayList<String>();

		votes.add(vote1);
		votes.add(vote2);
		votes.add(vote3);
		votes.add(vote4);
		votes.add(vote5);

		// We transform the votes above to byte[]
		List<byte[]> votesByte = new ArrayList<byte[]>();
		votesByte = Transformations.transformByteArrayStringToByteArray(votes);

		final CountingAlgorithm algorithm = new ReferendumAlgorithm();
		resultados = algorithm.count("1", votesByte);

		assert resultados != null;

		assert !resultados.isEmpty();

		assert ((ReferendumResult) resultados.get(1)).getNumeroSi() == 1;
		assert ((ReferendumResult) resultados.get(1)).getNumeroNo() == 1;
		assert ((ReferendumResult) resultados.get(0)).getNumeroSi() == 2;
		assert ((ReferendumResult) resultados.get(0)).getNumeroNo() == 0;
	}

	@Test
	public void testPredefinedCountAnswerZeroNo() throws Exception {

		List<Result> resultados;

		resultados = es.us.agoraus.counting.algorithms.Test.referendumAlgorithmTestVotation();

		assert !resultados.isEmpty();

		assert ((ReferendumResult) resultados.get(0)).getNumeroNo() == 0;

	}

	@Test
	public void testPredefinedCountAnswerZeroYes() throws Exception {

		List<Result> resultados;

		resultados = es.us.agoraus.counting.algorithms.Test.referendumAlgorithmTestVotation();

		assert !resultados.isEmpty();

		assert ((ReferendumResult) resultados.get(0)).getNumeroSi() == 2;

	}

	@Test
	public void testPredefinedCountAnswerOneNo() throws Exception {

		List<Result> resultados;

		resultados = es.us.agoraus.counting.algorithms.Test.referendumAlgorithmTestVotation();

		assert !resultados.isEmpty();

		assert ((ReferendumResult) resultados.get(1)).getNumeroNo() == 1;

	}

	@Test
	public void testPredefinedCountAnswerOneYes() throws Exception {

		List<Result> resultados;

		resultados = es.us.agoraus.counting.algorithms.Test.referendumAlgorithmTestVotation();

		assert !resultados.isEmpty();

		assert ((ReferendumResult) resultados.get(1)).getNumeroSi() == 1;

	}

}
