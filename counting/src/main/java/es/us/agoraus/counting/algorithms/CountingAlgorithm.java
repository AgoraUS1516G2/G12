package es.us.agoraus.counting.algorithms;

import java.util.List;

import es.us.agoraus.counting.dto.Result;

public interface CountingAlgorithm {

	List<Result> count(final String pollId, final List<byte[]> votesArr);

}
