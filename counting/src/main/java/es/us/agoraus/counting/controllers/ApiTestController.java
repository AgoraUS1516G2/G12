package es.us.agoraus.counting.controllers;

import java.util.List;

import org.apache.log4j.net.TelnetAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.us.agoraus.counting.algorithms.Algoritmo;
import es.us.agoraus.counting.algorithms.Test;
import es.us.agoraus.counting.domain.Resultado;
import es.us.agoraus.counting.domain.VotoAntiguo;
import es.us.agoraus.counting.domain.VotosCifrados;
import es.us.agoraus.counting.integration.StorageServiceImpl;

@RestController
@RequestMapping(value="/count")
public class ApiTestController {
	
	@Autowired
	StorageServiceImpl storageService;
	
	@RequestMapping("/predefined")
	public List<Resultado> predefinedCounting()
			throws Exception {

		List <Resultado> resultados = Test.naturalCountingAlgorithmTestVotation();
		
		return resultados;

	}

	@RequestMapping("/natural")
	public List<Resultado> naturalCounting(@RequestParam(value = "votationId", required = true) String votationId)
			throws Exception {

		VotosCifrados votos = storageService.getVotesForPoll(votationId);

		List<Resultado> resultados = Algoritmo.naturalCountingAlgorithm(votationId, votos.getVotes());
		
		return resultados;

	}

}