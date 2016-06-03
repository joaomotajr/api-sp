package br.gov.caixa.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.SegmentoNegocioDto;
import br.gov.caixa.api.model.SegmentoNegocio;
import br.gov.caixa.api.repository.SegmentoNegocioRepository;
import br.gov.caixa.api.result.SegmentoNegocioResult;

@Named
public class SegmentoNegocioService {

	@Inject
	SegmentoNegocioRepository repository;
	
	public SegmentoNegocioResult listAll() {
		List<SegmentoNegocio> lista = (repository.findAll());
		SegmentoNegocioResult result = new SegmentoNegocioResult();
		
		result.setList(SegmentoNegocioDto.fromSegmentoNegocioToListDto(lista));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	
	public SegmentoNegocioResult delete(SegmentoNegocioDto segmentoNegocioDto) {
		
		SegmentoNegocio segmentoNegocio = SegmentoNegocio.fromDtoToSegmentoNegocio(segmentoNegocioDto);
		
		repository.delete(segmentoNegocio);
		
		SegmentoNegocioResult result = new SegmentoNegocioResult();
		result.setMessage("Executado com sucesso.");
		return result;
	}
	
	public SegmentoNegocioResult save(SegmentoNegocioDto segmentoNegocioDto) {
		
		SegmentoNegocio segmentoNegocio = SegmentoNegocio.fromDtoToSegmentoNegocio(segmentoNegocioDto);
		
		repository.save(segmentoNegocio);
		
		SegmentoNegocioResult result = new SegmentoNegocioResult();
		result.setMessage("Executado com sucesso.");
		return result;
	}
}
