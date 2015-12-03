package es.us.agoraus.counting.controllers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.us.agoraus.counting.algorithms.Algoritmo;
import es.us.agoraus.counting.domain.Resultado;
import es.us.agoraus.counting.domain.VotoAntiguo;
import es.us.agoraus.counting.domain.VotosCifrados;
import es.us.agoraus.counting.integration.StorageServiceImpl;

@RestController
public class ApiTestController {
	
	@Autowired
	StorageServiceImpl storageService;

	@RequestMapping("/welcome")
	public String welcome(@RequestParam(value = "name", required = false, defaultValue = "people") String name) {
		String welcome = "Hello " + name
				+ ", welcome to our API Test, yours can use this URL for get your name in the sentence:"
				+ " http://localhost:8080/test/welcome?name=YourName";

		return welcome;
	}

	@RequestMapping("/test")
	public VotoAntiguo test(@RequestParam(value = "name", required = false, defaultValue = "John Dou") String name) {
		VotoAntiguo voto = new VotoAntiguo();
		voto.setNombre(name);
		voto.setEdad(35);
		voto.setPoblacion("Sevilla");

		return voto;
	}

	@RequestMapping(value = "/t", method = RequestMethod.POST)
	public void t(@RequestParam() String name) {
		System.out.println(name);
	}

	@RequestMapping("/recuento3")
	public List<Resultado> recuento3(@RequestParam(value = "idVotacion", required = true) String pollId)
			throws BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException {

		VotosCifrados votos = storageService.getVotesForPoll(pollId);

		List<Resultado> resultados = Algoritmo.algoritmo3(pollId, votos.getVotes());
		return resultados;

	}

}