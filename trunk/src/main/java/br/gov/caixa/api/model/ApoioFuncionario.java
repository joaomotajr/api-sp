package br.gov.caixa.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.caixa.api.dto.ApoioFuncionarioDto;

@Entity
@Table(name = "apoio_funcionario")
public class ApoioFuncionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "MATRICULA", nullable = false)
	private String matricula;
	
	@Column(name = "DATA_CEDES", nullable = true)
	private Date dataCedes;
	
	@Column(name = "DATA_ADMISSAO", nullable = true)
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
	
	public static ApoioFuncionario fromDtoToApoioFuncionario(ApoioFuncionarioDto apoioFuncionarioDto) {
		ApoioFuncionario apoioFuncionario = new ApoioFuncionario();
		apoioFuncionario.setUid(apoioFuncionarioDto.getUid());
		apoioFuncionario.setNome(apoioFuncionarioDto.getNome());
		apoioFuncionario.setMatricula(apoioFuncionarioDto.getMatricula());
		apoioFuncionario.setDataAdmissao(apoioFuncionarioDto.getDataAdmissao());
		apoioFuncionario.setDataCedes(apoioFuncionarioDto.getDataCedes());
		return apoioFuncionario;
	}
}
