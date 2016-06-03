package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.AtividadeDto;

public class AtividadeResult extends BasicResult {

	private List<AtividadeDto> list;
	private AtividadeDto atividade;

	public List<AtividadeDto> getList() {
		return list;
	}

	public void setList(List<AtividadeDto> list) {
		this.list = list;
	}

	public AtividadeDto getAtividade() {
		return atividade;
	}

	public void setAtividade(AtividadeDto atividade) {
		this.atividade = atividade;
	}

}
