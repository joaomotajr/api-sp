package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.FerramentaDto;

public class FerramentaResult extends BasicResult {

	private List<FerramentaDto> list;
	private FerramentaDto ferramenta;

	public List<FerramentaDto> getList() {
		return list;
	}

	public void setList(List<FerramentaDto> list) {
		this.list = list;
	}

	public FerramentaDto getFerramenta() {
		return ferramenta;
	}

	public void setFerramenta(FerramentaDto ferramenta) {
		this.ferramenta = ferramenta;
	}
}
