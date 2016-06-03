package br.gov.caixa.api.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.AuditoriaDto;
import br.gov.caixa.api.model.Auditoria;
import br.gov.caixa.api.repository.AuditoriaRepository;
import br.gov.caixa.api.result.AuditoriaResult;

@Named
public class AuditoriaService {

	@Inject
	AuditoriaRepository repository;
	
	public AuditoriaResult listAll() {
		
		List<Auditoria> lista = (repository.findAll());
		AuditoriaResult result = new AuditoriaResult();
		
		result.setList(AuditoriaDto.fromAuditoriaToListDto(lista));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	

	public AuditoriaResult save(AuditoriaDto auditoriaDto) {
		
		Auditoria auditoria = Auditoria.fromDtoToAuditoria(auditoriaDto);
		auditoria.setData(new Date());
		
		repository.save(auditoria);
		
		AuditoriaResult result = new AuditoriaResult();		
		result.setMessage("Executado com sucesso.");
		return result;
	}

	public AuditoriaResult delete(AuditoriaDto auditoriaDto) {
		// TODO Auto-generated method stub
		return null;
	}


	public AuditoriaResult listLogsPorCoordenacao(Long uid) {
		List<Auditoria> auditoria = repository.findByCoordenacao(uid);
		
		AuditoriaResult result;
		
		if (auditoria == null) {
			result = new AuditoriaResult();
			result.setMessage("Coordenação não Existe.");	
		}
		else {
						
			List<AuditoriaDto> dto = AuditoriaDto.fromAuditoriaToListDto(auditoria);
		
			result = new AuditoriaResult();		
			result.setList(dto);
			result.setMessage("Executado com sucesso.");		
		}
		
		return result;
	}

}
