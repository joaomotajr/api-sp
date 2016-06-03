package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.TimelineDto;

public class TimelineResult extends BasicResult {
	private List<TimelineDto> list;
	private TimelineDto Timeline;	

	public List<TimelineDto> getList() {
		return list;
	}

	public void setList(List<TimelineDto> list) {
		this.list = list;
	}

	public TimelineDto getTimeline() {
		return Timeline;
	}

	public void setTimeline(TimelineDto Timeline) {
		this.Timeline = Timeline;
	}
}
