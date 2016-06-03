package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.CategoriaTecnologicaDto;
import br.gov.caixa.api.result.CategoriaTecnologicaResult;
import br.gov.caixa.api.services.CategoriaTercnologicaService;

@RestController
public class CategoriaTecnologicaController {

	@Inject
	CategoriaTercnologicaService service;
	
	@RequestMapping(value = "/api/categoriaTecnologica/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CategoriaTecnologicaResult listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/api/categoriaTecnologica/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CategoriaTecnologicaResult save(@RequestBody CategoriaTecnologicaDto dto) {
		
		return service.save(dto);
	}
	
	@RequestMapping(value="/api/categoriaTecnologica/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CategoriaTecnologicaResult delete(@RequestBody CategoriaTecnologicaDto dto) {
		
		return service.delete(dto);
	}

}
