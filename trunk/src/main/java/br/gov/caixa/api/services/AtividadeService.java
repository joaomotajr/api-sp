package br.gov.caixa.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.AtividadeDto;
import br.gov.caixa.api.model.Atividade;
import br.gov.caixa.api.model.Disciplina;
import br.gov.caixa.api.repository.AtividadeRepository;
import br.gov.caixa.api.result.AtividadeResult;

@Named
public class AtividadeService {

	@Inject
	AtividadeRepository repository;

	public AtividadeResult listAll() {

		AtividadeResult result = new AtividadeResult();
		try {
			List<Atividade> lista = repository.findAll();

			if (lista != null) {

				result.setList(AtividadeDto.fromAtividadeToListDto(lista));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhum Ativo para a Coordenação.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public AtividadeResult listAtivosByIdDisciplina(Long idDisciplina) {
		AtividadeResult result = new AtividadeResult();	
		
		try {
			
			Disciplina disciplina = new Disciplina();
			disciplina.setUid(idDisciplina);
			
			List<Atividade> lista = repository.findByDisciplina(disciplina);				
			
			if (lista != null) {
								
				result.setList(AtividadeDto.fromAtividadeToListDto(lista));					
				result.setMessage("Executado com sucesso.");
			}
			else {
				result.setIsError(true);
				result.setMessage("Nenhum Atividade para a Disciplina.");
			}
		} catch (Exception e) {				 
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}				
	
		return result;
	}

	public AtividadeResult save(AtividadeDto dto) {
		Atividade atividade = Atividade.fromDtoToAtividade(dto);
		
		repository.save(atividade);
		
		AtividadeResult result = new AtividadeResult();
		result.setMessage("Executado com sucesso.");
		return result;
	}

	public AtividadeResult delete(AtividadeDto dto) {
		Atividade atividade = Atividade.fromDtoToAtividade(dto);
		AtividadeResult result;
		
		try {
			repository.delete(atividade);
			
			result = new AtividadeResult();
			result.setMessage("Executado com sucesso.");
			
		} catch (Exception e) {
			result = new AtividadeResult();
			
			result.setIsError(true);
			result.setMessage("Existe(m) Rating(s) Associados a esta Atividade.");
			
			e.printStackTrace();
		}		
		
		return result;
	}

}
