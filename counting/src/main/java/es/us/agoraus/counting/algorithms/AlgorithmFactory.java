package es.us.agoraus.counting.algorithms;

public class AlgorithmFactory {

	public static CountingAlgorithm forCriteria(SegmentationCriteria criteria) {
		CountingAlgorithm result = new ReferendumAlgorithm();
		if (criteria != null) {
			result = new SegmentationAlgorithm(criteria);
		}
		return result;
	}

}
