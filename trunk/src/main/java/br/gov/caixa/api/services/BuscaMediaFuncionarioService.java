package br.gov.caixa.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.BuscaMediaFuncionarioDto;
import br.gov.caixa.api.model.BuscaMediaFuncionario;
import br.gov.caixa.api.repository.BuscaMediaFuncionarioRepository;
import br.gov.caixa.api.result.BuscaMediaFuncionarioResult;

@Named
public class BuscaMediaFuncionarioService {
	
	@Inject
	private BuscaMediaFuncionarioRepository repository;
	
	public BuscaMediaFuncionarioResult findAll(){
		List<BuscaMediaFuncionario> lista = repository.findAll();
		BuscaMediaFuncionarioResult result = new BuscaMediaFuncionarioResult();
		result.setList(BuscaMediaFuncionarioDto.fromBuscaMediaFuncionarioToListDto(lista));
		result.setMessage("Executado com sucesso.");
		return result;
	}

}
