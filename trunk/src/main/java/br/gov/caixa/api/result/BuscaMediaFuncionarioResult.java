package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.BuscaMediaFuncionarioDto;

public class BuscaMediaFuncionarioResult extends BasicResult {

	private List<BuscaMediaFuncionarioDto> list;
	private BuscaMediaFuncionarioDto buscaMediaFuncionarioDto;

	public List<BuscaMediaFuncionarioDto> getList() {
		return list;
	}

	public void setList(List<BuscaMediaFuncionarioDto> list) {
		this.list = list;
	}

	public BuscaMediaFuncionarioDto getBuscaMediaFuncionarioDto() {
		return buscaMediaFuncionarioDto;
	}

	public void setBuscaMediaFuncionarioDto(
			BuscaMediaFuncionarioDto buscaMediaFuncionarioDto) {
		this.buscaMediaFuncionarioDto = buscaMediaFuncionarioDto;
	}

}
