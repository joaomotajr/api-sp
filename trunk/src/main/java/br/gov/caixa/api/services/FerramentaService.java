package br.gov.caixa.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.FerramentaDto;
import br.gov.caixa.api.model.CategoriaTecnologica;
import br.gov.caixa.api.model.Ferramenta;
import br.gov.caixa.api.repository.FerramentaRepository;
import br.gov.caixa.api.result.FerramentaResult;

@Named
public class FerramentaService {
	
	@Inject
	FerramentaRepository repository;
	
	public FerramentaResult listAll() {

		FerramentaResult result = new FerramentaResult();
		try {
			List<Ferramenta> lista = repository.findAll();

			if (lista != null) {
				result.setList(FerramentaDto.fromFerramentaToListDto(lista));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhuma ferramenta.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public FerramentaResult listFerramentasByIdCategoriaTecnologica(Long idCategoriaTecnologica) {
		FerramentaResult result = new FerramentaResult();
		try {
			
			CategoriaTecnologica categoriaTecnologica = new CategoriaTecnologica();
			categoriaTecnologica.setUid(idCategoriaTecnologica);
			
			List<Ferramenta> lista = repository.findByCategoriaTecnologica(categoriaTecnologica);				
			
			if (lista != null) {
				result.setList(FerramentaDto.fromFerramentaToListDto(lista));					
				result.setMessage("Executado com sucesso.");
			}
			else {
				result.setIsError(true);
				result.setMessage("Nenhuma Ferramenta para a categoria tecnologica.");
			}
		} catch (Exception e) {				 
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}				
	
		return result;
	}

	public FerramentaResult save(FerramentaDto dto) {
		Ferramenta ferramenta = Ferramenta.fromDtoToFerramenta(dto);
		repository.save(ferramenta);
		FerramentaResult result = new FerramentaResult();
		result.setMessage("Executado com sucesso.");
		return result;
	}

	public FerramentaResult delete(FerramentaDto dto) {
		Ferramenta ferramenta = Ferramenta.fromDtoToFerramenta(dto);
		FerramentaResult result;
		try {
			repository.delete(ferramenta);
			
			result = new FerramentaResult();
			result.setMessage("Executado com sucesso.");
			
		} catch (Exception e) {
			result = new FerramentaResult();
			
			result.setIsError(true);
			result.setMessage("Existe(m) Rating(s) Associados a esta Ferramenta.");
			
			e.printStackTrace();
		}		
		return result;
	}

}
