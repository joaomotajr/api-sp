package br.gov.caixa.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.AtivoDto;
import br.gov.caixa.api.model.Ativo;
import br.gov.caixa.api.repository.AtivoRepository;
import br.gov.caixa.api.result.AtivoResult;

@Named
public class AtivoService {
	
	@Inject
	AtivoRepository repository;
	
	public AtivoResult listAll() {
		List<Ativo> lista = (repository.findAll());
		AtivoResult result = new AtivoResult();
		
		result.setList(AtivoDto.fromAtivoToListDto(lista));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	
	public AtivoResult listAtivosByIdCoordenacao(Long idCoordenacao)
	{
		AtivoResult result = new AtivoResult();	
				
			try {
				List<Ativo> lista = repository.findByCoordenacao(idCoordenacao);				
				
				if (lista != null) {
									
					result.setList(AtivoDto.fromAtivoToListDto(lista));					
					result.setMessage("Executado com sucesso.");
				}
				else {
					result.setIsError(true);
					result.setMessage("Nenhum Ativo para a Coordenação.");
				}
			} catch (Exception e) {				 
				result.setIsError(true);
				result.setMessage(e.getMessage());
			}				
		
		return result;
	}
	
	public AtivoResult save(AtivoDto ativoDto) {
		
		Ativo ativo = Ativo.fromDtoToAtivo(ativoDto);
		
		repository.save(ativo);
		
		AtivoResult result = new AtivoResult();
		result.setMessage("Executado com sucesso.");
		return result;
	}

	public AtivoResult delete(AtivoDto ativoDto) {
		Ativo ativo = Ativo.fromDtoToAtivo(ativoDto);
		
		repository.delete(ativo);
		
		AtivoResult result = new AtivoResult();
		result.setMessage("Executado com sucesso.");
		return result;
	}

}
