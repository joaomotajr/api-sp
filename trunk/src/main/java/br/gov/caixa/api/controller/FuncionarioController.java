package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.FuncionarioDto;
import br.gov.caixa.api.result.FuncionarioResult;
import br.gov.caixa.api.services.FuncionarioService;

@RestController
public class FuncionarioController {
	
	@Inject
	FuncionarioService service;
	
	@RequestMapping(value="/api/funcionario/all", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResult listAll() {
		
		return service.listAll();
	}
	
	@RequestMapping(value="/api/funcionario/listFuncionarioPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResult listOne(@PathVariable Long uid) {
		
		return service.listOne(uid);
	}
	
	@RequestMapping(value="/api/funcionario/listFuncionariosPorCoordenacao/{coordenacaoUid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResult listFuncionariosPorCoordenacao(@PathVariable Long coordenacaoUid) {
		
		return service.listFuncionariosPorCoordenacao(coordenacaoUid);
	}	
		
	@RequestMapping(value="/api/funcionario/obterFuncionarioPorMatricula/{matricula}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResult listFuncionarioPorMatricula(@PathVariable String matricula) {
		
		return service.listFuncionarioPorMatricula(matricula);
	}
	
	@RequestMapping(value = "/api/funcionario/obterFuncionariosPorNome/{nome}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResult obterFuncionariosPorNome(@PathVariable String nome) {
		return service.obterFuncionariosPorNome(nome);
	}
	
	@RequestMapping(value="/api/funcionario/salvarFuncionario", 
			method=RequestMethod.POST,
			consumes = "application/json",  
			produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResult salvarFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
		
		return service.salvar(funcionarioDto);
	}
	
	@RequestMapping(value="/api/funcionario/deletarFuncionario/{funcionarioUid}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResult deletarFuncionario(@PathVariable Long funcionarioUid) {
		
		return service.deletar(funcionarioUid);
	}
	
	@RequestMapping(value="/api/funcionario/setaFuncionarioParaCoordenacao", 
			method=RequestMethod.POST,
			consumes = "application/json",  
			produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResult setaFuncionarioParaCoordenacao(@RequestBody FuncionarioDto funcionarioDto) {
		
		return service.setaFuncionarioParaCoordenacao(funcionarioDto);
	}
}
