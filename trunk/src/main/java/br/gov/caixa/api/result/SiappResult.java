package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.SiappDto;

public class SiappResult extends BasicResult  {
	private List<SiappDto> list;
	private SiappDto siapp;	

	public List<SiappDto> getList() {
		return list;
	}

	public void setList(List<SiappDto> list) {
		this.list = list;
	}

	public SiappDto getSiapp() {
		return siapp;
	}

	public void setSiapp(SiappDto siapp) {
		this.siapp = siapp;
	}
}
