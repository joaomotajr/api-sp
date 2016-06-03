package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.SegmentoNegocioDto;
import br.gov.caixa.api.result.SegmentoNegocioResult;
import br.gov.caixa.api.services.SegmentoNegocioService;

@RestController
public class SegmentoNegocioController {
	
	@Inject
	SegmentoNegocioService service;
	
	@RequestMapping(value="/api/segmentoNegocio/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SegmentoNegocioResult listAll() {
		
		return service.listAll();
	}
	
	@RequestMapping(value="/api/segmentoNegocio/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SegmentoNegocioResult delete(@RequestBody SegmentoNegocioDto segmentoNegocioDto) {
		
		return service.delete(segmentoNegocioDto);
	}
	
	@RequestMapping(value="/api/segmentoNegocio/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SegmentoNegocioResult save(@RequestBody SegmentoNegocioDto segmentoNegocioDto) {
		
		return service.save(segmentoNegocioDto);
	}

}
