package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.CoordenacaoDto;
import br.gov.caixa.api.result.CoordenacaoResult;
import br.gov.caixa.api.services.CoordenacaoService;


@RestController
public class CoordenacaoController {
	
	@Inject
	CoordenacaoService service;
	
	@RequestMapping(value="/api/coordenacao/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult listAll() {
		
		return service.listAll();
	}	
	
	@RequestMapping(value="/api/coordenacao/obterCoordenacaoPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult listCoordenacaoPorId(@PathVariable Long uid) {
		
		return service.listCoordenacaoPorId(uid);
	}
	
	@RequestMapping(value="/api/coordenacao/obterCoordenacoesPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult listCoordenacoesPorId(@PathVariable Long uid) {
		
		return service.listCoordenacoesPorId(uid);
	}
		
	@RequestMapping(value="/api/coordenacao/obterCoordenacaoPorNome/{nome}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult listOne(@PathVariable String nome) {
		
		return service.listCoordenacaoPorNome(nome);
	}
	
	@RequestMapping(value="/api/coordenacao/obterCoordenacoesTiPorIdCoordenador/{id}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult listCoordenacoesTiPorIdCoordenador(@PathVariable Long id) {
		
		return service.listCoordenacoesTiPorIdCoordenador(id);
	}
	
	@RequestMapping(value="/api/coordenacao/obterCoordenacoesPorIdCoordenador/{id}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult listCoordenacoesPorIdFuncionario(@PathVariable Long id) {
		
		return service.listCoordenacoesPorIdCoordenador(id);
	}
	                        
	@RequestMapping(value="/api/coordenacao/salvarCoordenacao", 
			method=RequestMethod.POST,
			consumes = "application/json",  
			produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult salvarCoordenacao(@RequestBody CoordenacaoDto coordenacaoDto) {
		
		return service.salvar(coordenacaoDto);
	}
	
	@RequestMapping(value="/api/coordenacao/setaCoordenador", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult setaCoordenador(@RequestBody CoordenacaoDto coordenacaoDto) {
		
		return service.setCoordenador(coordenacaoDto);
	}

	@RequestMapping(value="/api/coordenacao/setaParent/{id}/{parent}" , method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public int setaParent(@PathVariable Long id, @PathVariable Long parent) {
		
		return service.setParent(id, parent);
	}
	
	@RequestMapping(value="/api/coordenacao/setaDescricao/{id}/{descricao}" , method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public int setaDescricao(@PathVariable Long id, @PathVariable String descricao) {
		
		return service.setDescricao(descricao, id);
	}
	
	
	@RequestMapping(value="/api/coordenacao/obterCoordenacoesPorTipo/{tipoCoordenacao}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult listCoordenacoesPorIdFuncionario(@PathVariable String tipoCoordenacao) {
		
		return service.listaCoordenacaoPorTipo(tipoCoordenacao);
	}
	
	@RequestMapping(value="/api/coordenacao/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public CoordenacaoResult delete(@RequestBody CoordenacaoDto coordenacaoDto) {
		
		return service.delete(coordenacaoDto);
	}

}
