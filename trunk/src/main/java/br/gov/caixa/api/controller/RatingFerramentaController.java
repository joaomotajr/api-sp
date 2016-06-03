package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.result.RatingFerramentaResult;
import br.gov.caixa.api.services.RatingFerramentaService;

@RestController
public class RatingFerramentaController {
	
	@Inject
	RatingFerramentaService service;
	
	@RequestMapping(value = "/api/ratingFerramentas/obterRatingPorIdFuncionario/{idFuncionario}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public RatingFerramentaResult listByFuncionario(@PathVariable Long idFuncionario) {
		return service.listByFuncionario(idFuncionario);
	}
}
