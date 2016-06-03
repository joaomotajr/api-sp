package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.result.BuscaMediaFuncionarioResult;
import br.gov.caixa.api.services.BuscaMediaFuncionarioService;

@RestController
public class BuscaMediaFuncionarioController {
	
	@Inject
	private BuscaMediaFuncionarioService service;
	
	@RequestMapping(value="/api/buscaMediaFuncionario/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BuscaMediaFuncionarioResult listAll() {
		return service.findAll();
	}

}
