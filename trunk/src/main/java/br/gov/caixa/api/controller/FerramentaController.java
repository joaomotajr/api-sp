package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.FerramentaDto;
import br.gov.caixa.api.result.FerramentaResult;
import br.gov.caixa.api.services.FerramentaService;

@RestController
public class FerramentaController {
	
	@Inject
	FerramentaService service;
	
	@RequestMapping(value = "/api/ferramenta/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FerramentaResult listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/api/ferramenta/obterFerramentaPorIdCategoria/{idCategoria}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FerramentaResult obterAtivoPorIdCategoria(@PathVariable Long idCategoria) {
		
		return service.listFerramentasByIdCategoriaTecnologica(idCategoria);
	}
	
	@RequestMapping(value="/api/ferramenta/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FerramentaResult save(@RequestBody FerramentaDto dto) {
		
		return service.save(dto);
	}
	
	@RequestMapping(value="/api/ferramenta/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FerramentaResult delete(@RequestBody FerramentaDto dto) {
		
		return service.delete(dto);
	}

}
