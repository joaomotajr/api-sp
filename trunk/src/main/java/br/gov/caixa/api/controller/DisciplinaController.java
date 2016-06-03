package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.DisciplinaDto;
import br.gov.caixa.api.model.Metodologia;
import br.gov.caixa.api.result.DisciplinaResult;
import br.gov.caixa.api.services.DisciplinaService;

@RestController
public class DisciplinaController {

	@Inject
	DisciplinaService service;

	@RequestMapping(value = "/api/disciplina/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public DisciplinaResult listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/api/disciplina/obterDisciplinaPorMetodologia/{metodologia}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public DisciplinaResult listDisciplinaPorMetodologia(@PathVariable int metodologia) {
				
		Metodologia tipo = null;
		
		if (metodologia ==  0) {
			tipo = Metodologia.PUC;
		}
		else if (metodologia == 1) {
			tipo = Metodologia.AGIL;
		}
		else if (metodologia == 2) {
			tipo = Metodologia.ESTRUTURADA;
		}
		else if (metodologia == 3) {
			tipo = Metodologia.PORTAL_WEB;
		}else if (metodologia == 4) {
			tipo = Metodologia.DATAWAREHOUSE;
		}
		
		return service.listDisciplinaPorMetodologia(tipo);
	}
	
	@RequestMapping(value="/api/disciplina/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public DisciplinaResult save(@RequestBody DisciplinaDto disciplinaDto) {
		
		return service.save(disciplinaDto);
	}
	
	@RequestMapping(value="/api/disciplina/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public DisciplinaResult delete(@RequestBody DisciplinaDto disciplinaDto) {
		
		return service.delete(disciplinaDto);
	}


}
