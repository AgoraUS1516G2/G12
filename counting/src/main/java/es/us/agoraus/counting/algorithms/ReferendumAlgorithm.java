package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.us.agoraus.counting.dto.Answer;
import es.us.agoraus.counting.dto.ReferendumResult;
import es.us.agoraus.counting.dto.AlgorithmResult;
import es.us.agoraus.counting.dto.Vote;

public class ReferendumAlgorithm extends BaseAlgorithm {

	@Override
	protected List<AlgorithmResult> countingLogic(final List<Vote> votes) {
		final List<AlgorithmResult> results = new ArrayList<AlgorithmResult>();
		final Set<String> keys = new HashSet<String>();
		for (Vote v : votes) {
			for (Answer a : v.getAnswers()) {
				keys.add(a.getQuestion());
			}
		}
		for (String k : keys) {
			results.add(new ReferendumResult(k, 0, 0));
		}
		for (Vote v : votes) {
			for (AlgorithmResult r : results) {
				ReferendumResult refRes = (ReferendumResult) r;
				for (Answer a : v.getAnswers()) {
					if (a.getQuestion().equals(refRes.getQuestion())) {
						incrementCount(a.getAnwser(), refRes);
					}
				}
			}
		}
		return results;
	}

}
