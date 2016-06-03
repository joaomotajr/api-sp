package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.AuditoriaDto;
import br.gov.caixa.api.result.AuditoriaResult;
import br.gov.caixa.api.services.AuditoriaService;

@RestController
public class AuditoriaController {
	@Inject
	AuditoriaService service;
	
	@RequestMapping(value="/api/auditoria/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AuditoriaResult listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/api/auditoria/listLogsPorCoordenacao/{coordenacaoUid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AuditoriaResult listLogsPorCoordenacao(@PathVariable Long coordenacaoUid) {
		
		return service.listLogsPorCoordenacao(coordenacaoUid);
	}

	@RequestMapping(value="/api/auditoria/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AuditoriaResult save(@RequestBody AuditoriaDto auditoriaDto) {
		
		return service.save(auditoriaDto);
	}
	
	@RequestMapping(value="/api/auditoria/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AuditoriaResult delete(@RequestBody AuditoriaDto auditoriaDto) {
		
		return service.delete(auditoriaDto);
	}
}
