package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.FuncionarioDto;

public class FuncionarioResult extends BasicResult  {
	private List<FuncionarioDto> list;
	private FuncionarioDto funcionario;	

	public List<FuncionarioDto> getList() {
		return list;
	}

	public void setList(List<FuncionarioDto> list) {
		this.list = list;
	}

	public FuncionarioDto getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDto funcionario) {
		this.funcionario = funcionario;
	}	
}
