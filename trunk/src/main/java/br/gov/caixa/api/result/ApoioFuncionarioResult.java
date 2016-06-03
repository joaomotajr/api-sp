package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.ApoioFuncionarioDto;

public class ApoioFuncionarioResult extends BasicResult {

	private List<ApoioFuncionarioDto> list;
	private ApoioFuncionarioDto apoioFuncionario;

	public List<ApoioFuncionarioDto> getList() {
		return list;
	}

	public void setList(List<ApoioFuncionarioDto> list) {
		this.list = list;
	}

	public ApoioFuncionarioDto getApoioFuncionario() {
		return apoioFuncionario;
	}

	public void setApoioFuncionario(ApoioFuncionarioDto apoioFuncionario) {
		this.apoioFuncionario = apoioFuncionario;
	}
}
