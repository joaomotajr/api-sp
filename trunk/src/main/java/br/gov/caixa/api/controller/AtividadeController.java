package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.AtividadeDto;
import br.gov.caixa.api.result.AtividadeResult;
import br.gov.caixa.api.services.AtividadeService;

@RestController
public class AtividadeController {

	@Inject
	AtividadeService service;

	@RequestMapping(value = "/api/atividade/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AtividadeResult listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/api/atividade/obterAtividadePorIdDisciplina/{idDisciplina}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AtividadeResult obterAtivoPorIdDisciplina(@PathVariable Long idDisciplina) {
		
		return service.listAtivosByIdDisciplina(idDisciplina);
	}
	
	@RequestMapping(value="/api/atividade/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AtividadeResult save(@RequestBody AtividadeDto dto) {
		
		return service.save(dto);
	}
	
	@RequestMapping(value="/api/atividade/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AtividadeResult delete(@RequestBody AtividadeDto dto) {
		
		return service.delete(dto);
	}
}
