package br.gov.caixa.api.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.caixa.api.dto.SiappDto;
import br.gov.caixa.api.model.Siapp;
import br.gov.caixa.api.repository.SiappRepository;
import br.gov.caixa.api.result.GenericResult;
import br.gov.caixa.api.result.SiappResult;

@Named
public class SiappService {
	@Inject
	SiappRepository repository;
	
	@Inject
	EntityManager entityManager;
		
	public SiappResult delete(SiappDto siappDto) {
				
		Siapp siapp = new Siapp().fromDtoToSiapp(siappDto);
		
		repository.delete(siapp);
		
		SiappResult result = new SiappResult();
		result.setMessage("Executado com sucesso.");
		
		return result;
	}	

	
	public SiappResult salvarSistema(SiappDto siappDto) {

		SiappResult result = new SiappResult();
		
		try
		{
			Siapp siapp = new Siapp().fromDtoToSiapp(siappDto);		
			repository.save(siapp);	
		
			result.setMessage("Executado com sucesso.");
		}
		catch (Exception e) {				 
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
		
	
	public SiappResult salvarSistemas(List<SiappDto> siappDto) {

	
		for (SiappDto dto : siappDto) {
			
			Siapp siapp = repository.findByCodigo(dto.getCodigo());
			
			Siapp siappNew = new Siapp().fromDtoToSiapp(dto);
						
			if (siapp == null)
			{				
				repository.save(siappNew);
			}
			else
			{				
				siappNew.setUid(siapp.getUid());				
				repository.save(siappNew);			
			}
			
		}
		
		SiappResult result = new SiappResult();
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	
	
	public SiappResult listSistemasPorNomeCoordenacao(String nomeCoordenacao)
	{
 		SiappResult result = new SiappResult();	
				
			try {
				List<Siapp> lista = repository.findByCoordenacaoProjeto(nomeCoordenacao);				
				
				if (lista != null) {
									
					result.setList(SiappDto.fromSiappToListDto(lista));					
					result.setMessage("Executado com sucesso.");
				}
				else {
					result.setIsError(true);
					result.setMessage("Nenhum Sistema para a Coordenação.");
				}
			} catch (Exception e) {				 
				result.setIsError(true);
				result.setMessage(e.getMessage());
			}				
		
		return result;
	}
	
	
	@SuppressWarnings("unchecked")	
	public GenericResult listCoordenacaoSistemas() {
		Query query = entityManager.createQuery("Select s.coordenacaoTi, s.sistema, s.descricao from Siapp s order by s.coordenacaoTi");
		
		List<Object> listaSistemas = new ArrayList<Object>();
		
		listaSistemas = query.getResultList();

		GenericResult result = new GenericResult();
		
		result.setList(listaSistemas);
		result.setMessage("Executado com sucesso.");
		
		return result;
	}


	public SiappResult listAll() {
		List<Siapp> lista = (repository.findAll());
		SiappResult result = new SiappResult();
		
		result.setList(SiappDto.fromSiappToListDto(lista));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
}
