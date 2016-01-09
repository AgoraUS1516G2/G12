package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.us.agoraus.counting.dto.AlgorithmResult;
import es.us.agoraus.counting.dto.ReferendumResult;
import es.us.agoraus.counting.dto.Vote;

public class ReferendumAlgorithm extends BaseAlgorithm {

	@Override
	protected List<AlgorithmResult> countingLogic(List<Vote> votes) {
		final Map<String, ReferendumResult> questionsKeys = new HashMap<String, ReferendumResult>();
		votes.stream().map(vote -> vote.getAnswers()).flatMap(answerList -> answerList.stream()).forEach(a -> {
			ReferendumResult r;
			String question = a.getQuestion();
			if (!questionsKeys.keySet().contains(question)) {
				r = new ReferendumResult(question, 0, 0);
				questionsKeys.put(question, r);
			} else {
				r = questionsKeys.get(question);
			}
			incrementCount(a.getAnwser(), r);
		});
		return new ArrayList<AlgorithmResult>(questionsKeys.values());
	}

}
