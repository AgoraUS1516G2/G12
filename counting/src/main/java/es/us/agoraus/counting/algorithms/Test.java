package es.us.agoraus.counting.algorithms;
 
import java.util.ArrayList;
import java.util.List;

import es.us.agoraus.counting.domain.Resultado;
import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;
public class Test {
 
	public static List<Resultado> referendumAlgorithmTestVotation() throws Exception {
		
		Integer token;
		AuthorityImpl auth;
		List<Resultado> result;
		List<byte[]> encryptedVotes;
		
		encryptedVotes = new ArrayList<byte[]>();
		result= new ArrayList<Resultado>();
		auth = new AuthorityImpl();
		token = Token.calculateToken(1);
		
		String vote1 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"SI\"}"
                + "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";
        
        String vote2 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"NO\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

        String vote3 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"SI\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

        String vote4 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"SI\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";
        
        
        byte[] encryptedVote1 = auth.encrypt("1", vote1, token);
        byte[] encryptedVote2 = auth.encrypt("1", vote2, token);
        byte[] encryptedVote3 = auth.encrypt("1", vote3, token);
        byte[] encryptedVote4 = auth.encrypt("1", vote4, token);
        
        encryptedVotes.add(encryptedVote1);
        encryptedVotes.add(encryptedVote2);
        encryptedVotes.add(encryptedVote3);
        encryptedVotes.add(encryptedVote4);
        
        final CountingAlgorithm algorithm = new ReferendumAlgorithm();
        result = algorithm.count("1", encryptedVotes);
        
        return result;
	
	}
 
 
}
