package br.gov.caixa.api.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.SiappDto;
import br.gov.caixa.api.result.GenericResult;
import br.gov.caixa.api.result.SiappResult;
import br.gov.caixa.api.services.SiappService;

@RestController
public class SiappController {
	
	@Inject
	SiappService service;
    
	@RequestMapping(value="/api/siapp/salvarSistema", 
			method=RequestMethod.POST, 
			consumes = "application/json", 
			produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SiappResult salvarSistema(@RequestBody SiappDto siappDto) {
		
		return service.salvarSistema(siappDto);
	}
	
	@RequestMapping(value="/api/siapp/salvarSistemas", 
			method=RequestMethod.POST, 
			consumes = "application/json", 
			produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SiappResult salvarSistemas(@RequestBody List<SiappDto> siappDto) {
		
		return service.salvarSistemas(siappDto);
	}
	
	@RequestMapping(value="/api/siapp/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SiappResult delete(@RequestBody SiappDto siappDto) {
		
		return service.delete(siappDto);
	}
	
	@RequestMapping(value="/api/siapp/listCoordenacaoSistemas", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public GenericResult listCoordenacaoSistemas() {
		return service.listCoordenacaoSistemas();
	}
	
	@RequestMapping(value="/api/siapp/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SiappResult listAll() {
		return service.listAll();
	}	
	
	@RequestMapping(value="/api/siapp/obterSistemasPorNome/{nomeCoordenacao}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SiappResult obterSistemasPorNomeCoordenacao(@PathVariable String nomeCoordenacao) {
		
		return service.listSistemasPorNomeCoordenacao(nomeCoordenacao);
	}
	
}
