package br.gov.caixa.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.CoordenacaoDto;
import br.gov.caixa.api.model.Coordenacao;
import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.repository.CoordenacaoRepository;
import br.gov.caixa.api.repository.FuncionarioRepository;
import br.gov.caixa.api.result.CoordenacaoResult;

@Named
public class CoordenacaoService {

	@Inject
	CoordenacaoRepository repository;
	
	@Inject
	FuncionarioRepository funcionarioRepository;
	
	public CoordenacaoResult listAll() {
		List<Coordenacao> lista = (repository.findAll());
		CoordenacaoResult result = new CoordenacaoResult();
		
		result.setList(CoordenacaoDto.fromCoordenacaoToListDto(lista));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}

	public CoordenacaoResult listCoordenacaoPorNome(String nome) {
		Coordenacao coordenacao = repository.findByNome(nome);
		
		CoordenacaoResult result;
		
		if (coordenacao == null) {
			result = new CoordenacaoResult();
			result.setMessage("Coordenação não Existe.");	
		}
		else {
						
			CoordenacaoDto dto = CoordenacaoDto.fromCoordenacaoToDto(coordenacao);
		
			result = new CoordenacaoResult();		
			result.setCoordenacao(dto);
			result.setMessage("Executado com sucesso.");		
		}
		
		return result;
	}
	
	public CoordenacaoResult listCoordenacaoPorId(Long uid) {
		Coordenacao coordenacao = repository.findByUid(uid);
		
		CoordenacaoResult result;
		
		if (coordenacao == null) {
			result = new CoordenacaoResult();
			result.setMessage("Coordenação não Existe.");	
		}
		else {
						
			CoordenacaoDto dto = CoordenacaoDto.fromCoordenacaoToDto(coordenacao);
		
			result = new CoordenacaoResult();		
			result.setCoordenacao(dto);
			result.setMessage("Executado com sucesso.");		
		}
		
		return result;
	}	
	 
	public CoordenacaoResult listCoordenacoesTiPorIdCoordenador(Long id) {
		
		//Coordenacao coordenacao = obterCoodenacaoPorIdCoordenador(id);
		
		List<Coordenacao> coordenacao = repository.findByCoordenadorId(id);
		CoordenacaoResult result;
		
		if (coordenacao == null) {
			result = new CoordenacaoResult();
			result.setMessage("Coordenação não Existe.");	
		}
		else {
						
			List<CoordenacaoDto> dto = CoordenacaoDto.fromCoordenacaoToListDto(coordenacao);
		
			result = new CoordenacaoResult();		
			
			result.setList(dto);
			result.setMessage("Executado com sucesso.");		
		}
		
		return result;
	}

	private Coordenacao obterCoodenacaoPorIdCoordenador(Long id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setUid(id);
		
//		Coordenacao coordenacao = repository.findByCoordenador(funcionario);
//		List<Coordenacao> coordenacoes = repository.findBycoordenador_id(coordenacaoDto.getCoordenador_id());
//		return coordenacao;
		
		return null;
	}
	
	public CoordenacaoResult salvar(CoordenacaoDto dto) {
				
		Coordenacao coordenacao = Coordenacao.fromDtoToCoordenacao(dto);
		
		if(coordenacao.getParent() != null){
			Coordenacao parent = repository.findOne(coordenacao.getParent().getUid());
			coordenacao.setParent(parent);
		}
		
		try {
			
		
			coordenacao = repository.save(coordenacao);
			dto.setUid(coordenacao.getUid());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CoordenacaoResult result = new CoordenacaoResult();		
		result.setCoordenacao(dto);
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}
	
	public CoordenacaoResult delete(CoordenacaoDto dto) {
		
		Coordenacao coordenacao = Coordenacao.fromDtoToCoordenacao(dto);
		
		repository.delete(coordenacao);
		
		CoordenacaoResult result = new CoordenacaoResult();
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}	

	public CoordenacaoResult setCoordenador(CoordenacaoDto coordenacaoDto) {
		
		CoordenacaoResult result = new CoordenacaoResult();
		List<Coordenacao> coordenacoes = repository.findByCoordenadorId(coordenacaoDto.getCoordenadorId());		
				
		Coordenacao coordenacao = Coordenacao.fromDtoToCoordenacao(coordenacaoDto);				
				
		if (! coordenacoes.isEmpty()) {					
			
			String msgCoordenacoes = "";
			for (Coordenacao eachCoordenacao   : coordenacoes) {
								
				msgCoordenacoes += eachCoordenacao.getNome() + " " ;
			}
			
			repository.save(coordenacao);
			result.setIsError(true);
			result.setMessage("Inclusão realizada. Funcionário já é Coordenador da(s): " + msgCoordenacoes );
		}
		else {
			repository.save(coordenacao);
			result.setMessage("Inclusão realizada com sucesso.");
		}
		return result;
	}

	public int setParent(Long uid, Long parent) {
		
		return repository.setParentFor(parent, uid);
		
	}
	
	public int setDescricao(String descricao, Long uid) {
		
		int resp = repository.setDescricaoFor(descricao, uid);  
		return resp; 		
	}
	
	public CoordenacaoResult listCoordenacoesPorIdCoordenador(Long id) {
		
		Coordenacao coordenacao = obterCoodenacaoPorIdCoordenador(id);
		
		CoordenacaoResult result;
		
		if (coordenacao == null) {
			result = new CoordenacaoResult();
			result.setMessage("Nenhuma Coordenação Associada.");	
		}
		else {
			Coordenacao coordenacaoParent = new Coordenacao();		
			coordenacaoParent.setUid(coordenacao.getUid());		
			
			List<Coordenacao> lista = (repository.findByParent(coordenacaoParent));
			
			result = new CoordenacaoResult();
			
			result.setCoordenacao(CoordenacaoDto.fromCoordenacaoToDto(coordenacao));
			result.setList(CoordenacaoDto.fromCoordenacaoToListDto(lista));			
			result.setMessage("Executado com sucesso.");		
		}
		
		return result;
		
	}
	
	public CoordenacaoResult listCoordenacoesPorId(Long uid) {
				
		Coordenacao coordenacao = repository.findByUid(uid);
		
		CoordenacaoResult result;
		
		if (coordenacao == null) {
			result = new CoordenacaoResult();
			result.setMessage("Coordenação não Existe.");	
		}
		else {
			
			List<Coordenacao> lista = (repository.findByParent(coordenacao));
			
			result = new CoordenacaoResult();
			
			result.setCoordenacao(CoordenacaoDto.fromCoordenacaoToDto(coordenacao));
			result.setList(CoordenacaoDto.fromCoordenacaoToListDto(lista));			
			result.setMessage("Executado com sucesso.");
		}
		
		return result;
	}
	
	public CoordenacaoResult listaCoordenacaoPorTipo(String tipoCoordenacao) {
		List <Coordenacao> coordenacoes = repository.findByTipo(tipoCoordenacao);
		
		CoordenacaoResult result = new CoordenacaoResult();
		
		result.setMessage("Executado com sucesso.");
		
		List<CoordenacaoDto> coordenacoesDto = CoordenacaoDto.fromCoordenacaoToListDto(coordenacoes);
		result.setList(coordenacoesDto);
		
		return result;
	}
	
}
