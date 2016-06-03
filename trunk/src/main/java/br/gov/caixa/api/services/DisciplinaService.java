package br.gov.caixa.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.DisciplinaDto;
import br.gov.caixa.api.model.Disciplina;
import br.gov.caixa.api.model.Metodologia;
import br.gov.caixa.api.repository.DisciplinaRepository;
import br.gov.caixa.api.result.DisciplinaResult;

@Named
public class DisciplinaService {
	
	@Inject
	DisciplinaRepository repository;

	public DisciplinaResult listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public DisciplinaResult listDisciplinaPorMetodologia(Metodologia metodologia) {
		List <Disciplina> disciplinas = repository.findByMetodologia(metodologia);
		
		DisciplinaResult result = new DisciplinaResult();
		
		result.setMessage("Executado com sucesso.");
		
		List<DisciplinaDto> disciplinaDto = DisciplinaDto.fromDisciplinaToListDto(disciplinas);
		result.setList(disciplinaDto);
		
		return result;
	}

	public DisciplinaResult save(DisciplinaDto dto) {
		Disciplina disciplina = Disciplina.fromDtoToDisciplina(dto);
		
		repository.save(disciplina);
		
		DisciplinaResult result = new DisciplinaResult();
		result.setMessage("Executado com sucesso.");
		return result;
	}

	public DisciplinaResult delete(DisciplinaDto dto) {
		
		Disciplina disciplina = Disciplina.fromDtoToDisciplina(dto);		
		DisciplinaResult result;
		
		try {
			repository.delete(disciplina);
		
			result = new DisciplinaResult();
			result.setMessage("Executado com sucesso.");
			
		} catch (Exception e) { 
			result = new DisciplinaResult();
			
			result.setIsError(true);
			result.setMessage("Existe(m) Atividade(s) ou Rating(s) Associado(s) a esta Disciplina.");
			
			e.printStackTrace();
		}
		
		return result;
	}
	
}
