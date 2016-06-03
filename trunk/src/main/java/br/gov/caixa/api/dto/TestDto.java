package br.gov.caixa.api.dto;

import java.util.Date;

public class TestDto {

	private Long uid;
    private String descricao;
    private Date data;
    
    public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}