package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.us.agoraus.counting.domain.Answer;
import es.us.agoraus.counting.domain.PartialSegmentResult;
import es.us.agoraus.counting.domain.Result;
import es.us.agoraus.counting.domain.SegmentedResult;
import es.us.agoraus.counting.domain.Vote;

public class SegmentationAlgorithm extends BaseAlgorithm {

	private SegmentationCriteria criteria;

	public SegmentationAlgorithm(final SegmentationCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	protected List<Result> countingLogic(final List<Vote> votes) {
		final List<Result> result = new ArrayList<Result>();
		final Map<String, Result> questionsKeys = new HashMap<String, Result>();
		PartialSegmentResult partialRes;
		for (Vote v : votes) {
			for (Answer a : v.getAnswers()) {
				final String answer = a.getAnwser();
				final String question = a.getQuestion();
				final String segment = getSegment(v);
				if (!questionsKeys.keySet().contains(question)) {
					final SegmentedResult segRes = new SegmentedResult(question);
					questionsKeys.put(question, segRes);
					result.add(segRes);
					partialRes = segRes.addSegment(segment);
					incrementCount(answer, partialRes);
				} else {
					final Result r = questionsKeys.get(question);
					partialRes = ((SegmentedResult) r).getSegments().get(segment);
					if (partialRes == null) {
						partialRes = ((SegmentedResult) r).addSegment(segment);
					}
					incrementCount(answer, partialRes);
				}
			}
		}
		return result;
	}

	public SegmentationCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SegmentationCriteria criteria) {
		this.criteria = criteria;
	}

	private String getSegment(final Vote vote) {
		String result = null;
		switch (criteria) {
		case age:
			result = vote.getAge();
			break;
		case gender:
			result = vote.getGenre();
			break;
		case aut_com:
			result = vote.getAutonomousCommunity();
		}
		return result;
	}

}
