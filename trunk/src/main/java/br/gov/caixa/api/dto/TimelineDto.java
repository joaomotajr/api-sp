package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.gov.caixa.api.model.Timeline;
import br.gov.caixa.api.model.TipoTimeline;

public class TimelineDto {
	
	private Long uid;	
	private FuncionarioDto funcionarioDto;	
	private Date data;
	private String titulo;
	private String detalhe;
	private TipoTimeline tipoTimeline;	

	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public FuncionarioDto getFuncionarioDto() {
		return funcionarioDto;
	}
	
	public void setFuncionarioDto(FuncionarioDto funcionarioDto) {
		this.funcionarioDto = funcionarioDto;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDetalhe() {
		return detalhe;
	}
	
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	
	public TipoTimeline getTipoTimeline() {
		return tipoTimeline;
	}

	public void setTipoTimeline(TipoTimeline tipoTimeline) {
		this.tipoTimeline = tipoTimeline;
	}
	
	public static TimelineDto fromTimelineToDto(Timeline timeline) {
		
		TimelineDto dto = new TimelineDto();
		
		dto.setUid(timeline.getUid());		
		dto.setData(timeline.getData());
		dto.setTitulo(timeline.getTitulo());
		dto.setDetalhe(timeline.getDetalhe());
		dto.setTipoTimeline(timeline.getTipoTimeline());
						
		return dto;
	}
	
	public static List<TimelineDto> fromTimelineToListDto(List<Timeline> list) {
		
		List<TimelineDto> returnList = new ArrayList<TimelineDto>();
		
		for (Timeline timeline   : list) {
			TimelineDto dto = new TimelineDto();
		
			dto.setUid(timeline.getUid());			
			dto.setTitulo(timeline.getTitulo());
			dto.setData(timeline.getData());
			dto.setDetalhe(timeline.getDetalhe());
			dto.setTipoTimeline(timeline.getTipoTimeline());
				
			returnList.add(dto);
		}
		
		return returnList;
	}

}
