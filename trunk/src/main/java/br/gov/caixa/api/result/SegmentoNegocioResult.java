package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.SegmentoNegocioDto;

public class SegmentoNegocioResult extends BasicResult {

	private List<SegmentoNegocioDto> list;

	public List<SegmentoNegocioDto> getList() {
		return list;
	}

	public void setList(List<SegmentoNegocioDto> list) {
		this.list = list;
	}	
}