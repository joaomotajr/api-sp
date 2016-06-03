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

import br.gov.caixa.api.dto.ProfileDto;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "OBJETIVO")
	private String objetivo;	

	@Column(name = "QUALIFICACAO")
	private String qualificacao;
	
	@Column(name = "ANOTACOES")
	private String anotacoes;
			
	@Column(name = "FORMACAO")
	private String formacao;
	
	@Column(name = "EXPERIENCIA")
	private String experiencia;		
	
	@Column(name = "TREINAMENTO")
	private String treinamento;	
	
	@Column(name = "CERTIFICACAO")
	private String certificacao;
	
	@Column(name = "DATA_ADMISSAO", nullable = true)
	private Date dataAdmissao;
	
	@Column(name = "HORA_ENTRADA", nullable = true)
	private String horaEntrada;
	
	@Column(name = "HORA_SAIDA", nullable = true)
	private String horaSaida;
	
	@OneToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FUNCIONARIO_ID", nullable = false)
	private Funcionario funcionario;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	
	public String getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	
	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public String getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(String treinamento) {
		this.treinamento = treinamento;
	}

	public String getCertificacao() {
		return certificacao;
	}

	public void setCertificacao(String certificacao) {
		this.certificacao = certificacao;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public static Profile fromDtoToProfile(ProfileDto dto) {
		
		Profile profile = new Profile();
		
		profile.setUid(dto.getUid());
		profile.setObjetivo(dto.getObjetivo());
		profile.setFormacao(dto.getFormacao());		
		profile.setAnotacoes(dto.getAnotacoes());
		profile.setQualificacao(dto.getQualificacao());
		profile.setExperiencia(dto.getExperiencia());
		profile.setTreinamento(dto.getTreinamento());
		profile.setCertificacao(dto.getCertificacao());
		profile.setDataAdmissao(dto.getDataAdmissao());
		profile.setHoraEntrada(dto.getHoraEntrada());
		profile.setHoraSaida(dto.getHoraSaida());
		
		if (dto.getFuncionarioDto() != null) {	 
			
			Funcionario funcionario = new Funcionario();
			funcionario.setUid(dto.getFuncionarioDto().getUid());
			profile.setFuncionario(funcionario);			
		}		
		
		return profile;
	}

}