package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.CoordenacaoDto;

public class CoordenacaoResult extends BasicResult {

	private List<CoordenacaoDto> list;
	private CoordenacaoDto coordenacao;

	public List<CoordenacaoDto> getList() {
		return list;
	}

	public void setList(List<CoordenacaoDto> list) {
		this.list = list;
	}

	public CoordenacaoDto getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(CoordenacaoDto coordenacao) {
		this.coordenacao = coordenacao;
	}	

}