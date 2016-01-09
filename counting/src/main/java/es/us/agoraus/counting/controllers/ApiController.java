package es.us.agoraus.counting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.us.agoraus.counting.algorithms.AlgorithmFactory;
import es.us.agoraus.counting.algorithms.CountingAlgorithm;
import es.us.agoraus.counting.algorithms.SegmentationCriteria;
import es.us.agoraus.counting.algorithms.Test;
import es.us.agoraus.counting.algorithms.Transformations;
import es.us.agoraus.counting.domain.EncryptedVotes;
import es.us.agoraus.counting.domain.Result;
import es.us.agoraus.counting.integration.StorageServiceImpl;

@RestController
@RequestMapping(value = "/count")
public class ApiController {

	@Autowired
	StorageServiceImpl storageService;

	/**
	 * The following method is used to test the algorithm. We simulate a
	 * votation, creating the some votes json and crypting them. After that we
	 * call the method as if we obtained that crypted votes from the database.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/predefined")
	public List<Result> predefinedCounting() throws Exception {
		List<Result> result = Test.referendumAlgorithmTestVotation();
		return result;
	}

	/**
	 * The following method computes a votation retrieving the encrypted votes
	 * from a database. We offer two ways to code the votes of a certain
	 * votation. After we obtain the votes, they are transformed in order to the
	 * codification obtained in the method call, and finally it runs the
	 * algorithm where the decrypt is done and the votes are counted.
	 * 
	 * @param pollId
	 * @param codification
	 * @param segment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{pollId}")
	public List<Result> referendum(@PathVariable String pollId,
			@RequestParam(value = "cod", required = false) String codification,
			@RequestParam(value = "segment", required = false) SegmentationCriteria segment) {
		EncryptedVotes votes = storageService.getVotesForPoll(pollId);
		List<byte[]> byteVotes = Transformations.forCodification(codification, votes.getVotes());
		CountingAlgorithm algorithm = AlgorithmFactory.forCriteria(segment);
		List<Result> result = algorithm.count(pollId, byteVotes);
		return result;
	}

	@RequestMapping("/{pollId}/charts")
	public ModelAndView chart(@PathVariable String pollId,
			@RequestParam(value = "cod", required = false) String codification,
			@RequestParam(value = "segment", required = false) SegmentationCriteria segment) {
		final ModelAndView model = new ModelAndView("non-segmented-charts");
		if (segment != null) {
			model.addObject("criteria", segment);
			model.setViewName("segmented-charts");
		}
		final List<Result> result = referendum(pollId, codification, segment);
		model.addObject("data", result);
		return model;
	}

}