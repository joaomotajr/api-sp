package br.gov.caixa.api.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.FuncionarioDto;
import br.gov.caixa.api.model.Coordenacao;
import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.repository.FuncionarioRepository;
import br.gov.caixa.api.result.FuncionarioResult;

@Named
public class FuncionarioService {
	
	@Inject
	private FuncionarioRepository repository;
		
	public FuncionarioResult listAll() {
		List<Funcionario> lista = (repository.findAll());
		FuncionarioResult result = new FuncionarioResult();
		
		result.setList(FuncionarioDto.fromFuncionarioToListDto(lista));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	
	public FuncionarioResult listOne(Long uid) {
		Funcionario funcionario = (repository.findByUid(uid));
		FuncionarioResult result = new FuncionarioResult();
		
		result.setFuncionario(FuncionarioDto.fromFuncionarioToDto(funcionario));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	
	public FuncionarioResult listFuncionariosPorCoordenacao(Long coordenacaoUid) {
		
		Coordenacao coordenacao = new Coordenacao();
		coordenacao.setUid(coordenacaoUid);
		
		List<Funcionario> lista = repository.findByCoordenacao(coordenacao);
		
		FuncionarioResult result = new FuncionarioResult();
		
		result.setList(FuncionarioDto.fromFuncionarioToListDto(lista));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}

	public FuncionarioResult listFuncionarioPorMatricula(String matricula) {
		Funcionario funcionario = repository.findByMatricula(matricula);		
		
		FuncionarioResult result;
		
		if (funcionario != null) {
			
			FuncionarioDto dto = FuncionarioDto.fromFuncionarioToDto(funcionario);
			
			result = new FuncionarioResult();		
			result.setFuncionario(dto);
			result.setMessage("Executado com sucesso.");
		}
		else {
			result = new FuncionarioResult();
			result.setMessage("Funcionário não Existe.");
		}		
		
		return result;
	}
	
	public FuncionarioResult salvar(FuncionarioDto dto) {
		
		Funcionario funcionario = repository.findByMatricula(dto.getMatricula());
		
		FuncionarioResult result;
		
		if (funcionario == null) {			
		
			funcionario = Funcionario.fromDtoToFuncionario(dto);
			
			funcionario = repository.save(funcionario);
			dto.setUid(funcionario.getUid());
			
			result = new FuncionarioResult();		
			result.setFuncionario(dto);
			
			result.setMessage("Executado com sucesso.");
		}
		else
		{
			dto.setUid(funcionario.getUid());
			
			result = new FuncionarioResult();	
			result.setFuncionario(dto);			
			result.setMessage("Funcionário Já Existe.");
		}
		
		return result;
	}
	
	public FuncionarioResult setaFuncionarioParaCoordenacao(FuncionarioDto dto) {
		
		Funcionario funcionario = repository.findByMatricula(dto.getMatricula());		
		FuncionarioResult result;
		
		if (funcionario == null) {			
		
			funcionario = Funcionario.fromDtoToFuncionario(dto);
			
			funcionario = repository.save(funcionario);
			dto.setUid(funcionario.getUid());
			
			result = new FuncionarioResult();		
			result.setFuncionario(dto);
			result.setMessage("Executado com sucesso.");
		}
		else
		{
			result = new FuncionarioResult();
			
			if(funcionario.getCoordenacao() == null)
			{	
				funcionario.setCoordenacao(Coordenacao.fromDtoToCoordenacao(dto.getCoordenacaoDto()));				
				repository.save(funcionario);
				
				dto = FuncionarioDto.fromFuncionarioToDto(funcionario);				
				result.setFuncionario(dto);
				result.setMessage("Executado com sucesso.");
			}			
			else if(dto.getCoordenacaoDto().getUid() == funcionario.getCoordenacao().getUid() )
			{			
				result.setIsError(true);
				result.setMessage("Ja Existe.");
			}
			else if(funcionario.getCoordenacao().getUid() != null )
			{	
				result.setIsError(true);
				result.setMessage(funcionario.getCoordenacao().getNome());
			}
		}
		
		return result;
	}

	
	public FuncionarioResult deletar(Long funcionarioUid) {		
	
		FuncionarioResult result = new FuncionarioResult();
		try {			
			
			repository.delete(funcionarioUid);			
			result.setMessage("Funcionário Excluído.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result; 

	}

	public FuncionarioResult obterFuncionariosPorNome(String nome) {
		FuncionarioResult result = new FuncionarioResult();
		
		List<String> nomes = new ArrayList<String>();
		nomes.add("Joao");
		nomes.add("Mota");		
		
		try {
			List<Funcionario> funcionarios = repository.findByNomeContainingIgnoreCase(nome);			
			
			if (funcionarios != null) {
				result.setList(FuncionarioDto.fromFuncionarioToListDto(funcionarios));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhum Funcionario Encontrado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
