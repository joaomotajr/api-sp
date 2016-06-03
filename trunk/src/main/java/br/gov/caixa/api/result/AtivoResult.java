package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.AtivoDto;

public class AtivoResult extends BasicResult {

	private List<AtivoDto> list;
	private AtivoDto ativo;

	public List<AtivoDto> getList() {
		return list;
	}

	public void setList(List<AtivoDto> list) {
		this.list = list;
	}

	public AtivoDto getAtivo() {
		return ativo;
	}

	public void setAtivo(AtivoDto ativo) {
		this.ativo = ativo;
	}
}
