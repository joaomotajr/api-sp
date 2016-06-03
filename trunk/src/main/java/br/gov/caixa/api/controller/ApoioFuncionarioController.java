package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.result.ApoioFuncionarioResult;
import br.gov.caixa.api.services.ApoioFuncionarioService;

@RestController
public class ApoioFuncionarioController {
	
	@Inject
	private ApoioFuncionarioService service;
	
	@RequestMapping(value="/api/apoioFuncionario/listApoioFuncionarioPorMatricula/{matricula}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ApoioFuncionarioResult listOne(@PathVariable String matricula) {
		
		return service.listOne(matricula);
	}

}
