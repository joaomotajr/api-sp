package br.gov.caixa.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.CategoriaTecnologicaDto;
import br.gov.caixa.api.model.CategoriaTecnologica;
import br.gov.caixa.api.repository.CategoriaTecnologicaRepository;
import br.gov.caixa.api.result.CategoriaTecnologicaResult;

@Named
public class CategoriaTercnologicaService {
	
	@Inject
	CategoriaTecnologicaRepository repository;
	
	public CategoriaTecnologicaResult listAll() {

		CategoriaTecnologicaResult result = new CategoriaTecnologicaResult();
		try {
			List<CategoriaTecnologica> lista = repository.findAll();

			if (lista != null) {
				result.setList(CategoriaTecnologicaDto.fromCategoriaTecnologicaToListDto(lista));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhuma CategoriaTecnologica.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public CategoriaTecnologicaResult save(CategoriaTecnologicaDto dto) {
		CategoriaTecnologica categoriaTecnologica = CategoriaTecnologica.fromDtoToCategoriaTecnologica(dto);
		repository.save(categoriaTecnologica);
		CategoriaTecnologicaResult result = new CategoriaTecnologicaResult();
		result.setMessage("Executado com sucesso.");
		return result;
	}

	public CategoriaTecnologicaResult delete(CategoriaTecnologicaDto dto) {
		CategoriaTecnologica categoriaTecnologica = CategoriaTecnologica.fromDtoToCategoriaTecnologica(dto);
		CategoriaTecnologicaResult result;
		try {
			repository.delete(categoriaTecnologica);
			
			result = new CategoriaTecnologicaResult();
			result.setMessage("Executado com sucesso.");
			
		} catch (Exception e) {
			result = new CategoriaTecnologicaResult();
			
			result.setIsError(true);
			result.setMessage("Existe(m) Rating(s) Associados a esta categoria tecnologica.");
			
			e.printStackTrace();
		}		
		return result;
	}

}
