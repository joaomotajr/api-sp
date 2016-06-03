package br.gov.caixa.api.controller;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.TimelineDto;
import br.gov.caixa.api.result.TimelineResult;
import br.gov.caixa.api.services.TimelineService;

@RestController
public class TimelineController {
	@Inject
	TimelineService service;
	
	@RequestMapping(value="/api/timeline/all", 
	method=RequestMethod.GET, 
	produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public TimelineResult listAll() {
		return service.listAll();
	}	
	
	@RequestMapping(value="/api/timeline/save", 
	method=RequestMethod.POST, 
	consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public TimelineResult save(@RequestBody TimelineDto timelineDto) {
		
		return service.save(timelineDto);
	}
	
	@RequestMapping(value="/api/timeline/delete", 
	method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public TimelineResult delete(@RequestBody TimelineDto timelineDto) {
		
		return service.delete(timelineDto);
	}		
	
	@RequestMapping(value="/api/timeline/obterTimelinePorFuncionario/{id}", 
	method=RequestMethod.GET, 
	produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public TimelineResult listTimelinePorFuncionario(@PathVariable Long id) {
		
		return service.listTimelinePorFuncionario(id);
	}
	
	@RequestMapping(value="/api/timeline/obterTimelinePorFuncionarioMaiorData/{id}/{dataAdmissao}", 
	method=RequestMethod.GET, 
	produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public TimelineResult listTimelinePorFuncionarioMaiorData(@PathVariable Long id, @PathVariable Date dataAdmissao) {
		
		return service.listTimelinePorFuncionarioMaiorData(id, dataAdmissao);
	}
	
	@RequestMapping(value="/api/timeline/obterTimelinePorFuncionarioMenorData/{id}/{dataAdmissao}", 
	method=RequestMethod.GET, 
	produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public TimelineResult listTimelinePorFuncionarioMenorData(@PathVariable Long id, @PathVariable Date dataAdmissao) {
		
		return service.listTimelinePorFuncionarioMenorData(id, dataAdmissao);
	}
}
