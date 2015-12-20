package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import es.us.agoraus.counting.domain.Answer;
import es.us.agoraus.counting.domain.ReferendumResult;
import es.us.agoraus.counting.domain.Result;
import es.us.agoraus.counting.domain.Vote;
import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;

public class Algoritmo {

	/**
	 * This function does the natural counting algorithm.
	 * 
	 * @param surveyId.
	 *            The id of the survey obtained in the controller
	 * @param votos
	 * @return
	 * @throws Exception
	 */
	public static List<Result> naturalCountingAlgorithm(String votationId, List<byte[]> votos) throws Exception {

		
		// First, the variables the algorithm needs are created
		Integer token;
		Integer numericSurveyId;
		AuthorityImpl auth;
		List<Vote> votes;
		boolean checkVote;

		// Second, the variables are initialized
		numericSurveyId = Integer.valueOf(votationId);
		token = Token.calculateToken(numericSurveyId);
		auth = new AuthorityImpl();
		votes = new ArrayList<Vote>();
		

		// Third,
		for (byte[] s : votos) {

			// bytesDecode = decoder.decodeBuffer(s);
			checkVote = auth.checkVote(s, votationId, token);
			if (checkVote) {
				String res = null;
				res = auth.decrypt(votationId, s, token);
				
				// Voto voto = mapper.readValue(res,new
				// TypeReference<Voto>() {});

				Gson gson = new Gson();
				Vote vot = gson.fromJson(res, Vote.class);
				votes.add(vot);

			}

		}
		Set<String> claves = new HashSet<String>();
		for (Vote v : votes) {
			for (Answer a : v.getAnswers()) {
				claves.add(a.getQuestion());
			}
		}
		List<Result> resultados = new ArrayList<Result>();
		for (String c : claves) {
			resultados.add(new ReferendumResult(c, 0, 0));

		}
		for (Vote v : votes) {
			for (Result r : resultados) {
				for (Answer a : v.getAnswers()) {

					if (a.getQuestion().equals(((ReferendumResult) r).getPregunta())) {

						if (a.getAnswer_question().equals("SI")) {
							((ReferendumResult) r).setNumeroSi(((ReferendumResult) r).getNumeroSi() + 1);
						} else if (a.getAnswer_question().equals("NO")) {
							((ReferendumResult) r).setNumeroNo(((ReferendumResult) r).getNumeroNo() + 1);
						}
					}
				}
			}
		}

		return resultados;
	}
}