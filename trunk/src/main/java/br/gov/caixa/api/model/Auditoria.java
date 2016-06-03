package br.gov.caixa.api.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.caixa.api.dto.AuditoriaDto;

@Entity
@Table(name = "auditoria")
public class Auditoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;		

	@OneToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="COORDENACAO_ID", nullable = false)
	private Coordenacao coordenacao;
	
	@OneToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FUNCIONARIO_ID", nullable = false)
	private Funcionario funcionario;
	
	@Column(name = "DETALHE")
	private String detalhe;
	
	@Column(name = "ACAO")
	private String acao;	
	
	@Column(name = "DATA")
	private Date data;
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}	
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}	
	
	public static Auditoria fromDtoToAuditoria(AuditoriaDto dto) {
		
		Auditoria auditoria = new Auditoria();
		
		auditoria.setUid(dto.getUid());		
		auditoria.setDetalhe(dto.getDetalhe());
		
		if (dto.getCoordenacaoDto() != null) {	 
			
			Coordenacao coordenacao = new Coordenacao();
			coordenacao.setUid(dto.getCoordenacaoDto().getUid());
			auditoria.setCoordenacao(coordenacao);			
		}
		
		if (dto.getFuncionarioDto() != null) {	 
			
			Funcionario funcionario = new Funcionario();
			funcionario.setUid(dto.getFuncionarioDto().getUid());
			auditoria.setFuncionario(funcionario);			
		}
		
		auditoria.setAcao(dto.getAcao());		
		auditoria.setData(dto.getData());		
		
		return auditoria;
	}
}
