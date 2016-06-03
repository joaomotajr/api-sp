package br.gov.caixa.api.services;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.ApoioFuncionarioDto;
import br.gov.caixa.api.model.ApoioFuncionario;
import br.gov.caixa.api.repository.ApoioFuncionarioRepository;
import br.gov.caixa.api.result.ApoioFuncionarioResult;

@Named
public class ApoioFuncionarioService {
	
	@Inject
	ApoioFuncionarioRepository repository;
	
	public ApoioFuncionarioResult listOne(String matricula) {
		ApoioFuncionario apoioFuncionario = (repository.findByMatricula(matricula));
		ApoioFuncionarioResult result = new ApoioFuncionarioResult();
		
		result.setApoioFuncionario(ApoioFuncionarioDto.fromApoioFuncionarioToDto(apoioFuncionario));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	

}
