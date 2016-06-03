package br.gov.caixa.api.dto;

import java.util.Date;

import br.gov.caixa.api.model.ApoioFuncionario;

public class ApoioFuncionarioDto {

	private Long uid;
	private String nome;
	private String matricula;
	private Date dataCedes;
	private Date dataAdmissao;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getDataCedes() {
		return dataCedes;
	}

	public void setDataCedes(Date dataCedes) {
		this.dataCedes = dataCedes;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public static ApoioFuncionarioDto fromApoioFuncionarioToDto(ApoioFuncionario apoioFuncionario) {
		
		ApoioFuncionarioDto dto = new ApoioFuncionarioDto();
		dto.setUid(apoioFuncionario.getUid());
		dto.setNome(apoioFuncionario.getNome());
		dto.setMatricula(apoioFuncionario.getMatricula());
		dto.setDataAdmissao(apoioFuncionario.getDataAdmissao());
		dto.setDataCedes(apoioFuncionario.getDataCedes());
			
		return dto;
	}
}
