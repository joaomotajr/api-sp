package br.gov.caixa.api.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.TimelineDto;
import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.Timeline;
import br.gov.caixa.api.repository.TimelineRepository;
import br.gov.caixa.api.result.TimelineResult;

@Named
public class TimelineService {
	@Inject
	TimelineRepository repository;

	public TimelineResult listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public TimelineResult save(TimelineDto dto) {
		Timeline timeline = Timeline.fromDtoToTimeline(dto);
		
		timeline = repository.save(timeline);
		
		TimelineResult result = new TimelineResult();
		
		dto.setUid(timeline.getUid());
		result.setTimeline(dto);
		
		result.setMessage("Executado com sucesso.");
		
		return result;
	}

	public TimelineResult delete(TimelineDto dto) {
				
		Timeline timeline = new Timeline();
		timeline = Timeline.fromDtoToTimeline(dto);
		
		TimelineResult result = new TimelineResult();
		
		try {
			repository.delete(timeline);
			result.setMessage("Executado com sucesso.");			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
				
		return result;
	}

	public TimelineResult listTimelinePorFuncionario(Long id) {

		Funcionario funcionario = new Funcionario();
		funcionario.setUid(id);
		
		List<Timeline> list = repository.findByFuncionario(funcionario);
		
		TimelineResult result = new TimelineResult();
		result.setList(TimelineDto.fromTimelineToListDto(list));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}

	public TimelineResult listTimelinePorFuncionarioMaiorData(Long id, Date dataAdmissao) {
	
		List<Timeline> list = repository.findByFuncionarioMaiorData(id, dataAdmissao);
		
		TimelineResult result = new TimelineResult();
		result.setList(TimelineDto.fromTimelineToListDto(list));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}

	public TimelineResult listTimelinePorFuncionarioMenorData(Long id, Date dataAdmissao) {
		
		List<Timeline> list = repository.findByFuncionarioMenorData(id, dataAdmissao);
		
		TimelineResult result = new TimelineResult();
		result.setList(TimelineDto.fromTimelineToListDto(list));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	
	
}
