package br.gov.caixa.api.result;

import java.util.List;

public class GenericResult extends BasicResult {
		
	private List<?> list;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}	

	
}
