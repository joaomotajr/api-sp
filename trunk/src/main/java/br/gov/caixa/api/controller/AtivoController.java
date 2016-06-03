package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.AtivoDto;
import br.gov.caixa.api.result.AtivoResult;
import br.gov.caixa.api.services.AtivoService;

@RestController
public class AtivoController {
	
	@Inject
	AtivoService service;
	
	@RequestMapping(value="/api/ativo/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AtivoResult listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/api/ativo/obterAtivosPorIdCoordenacao/{idCoordenacao}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AtivoResult obterAtivoPorIdCoordenacao(@PathVariable Long idCoordenacao) {
		
		return service.listAtivosByIdCoordenacao(idCoordenacao);
	}
	
	@RequestMapping(value="/api/ativo/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AtivoResult save(@RequestBody AtivoDto ativoDto) {
		
		return service.save(ativoDto);
	}
	
	@RequestMapping(value="/api/ativo/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AtivoResult delete(@RequestBody AtivoDto ativoDto) {
		
		return service.delete(ativoDto);
	}
}
