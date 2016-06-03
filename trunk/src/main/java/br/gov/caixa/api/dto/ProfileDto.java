package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.Profile;

public class ProfileDto {
	private Long uid;
	private String objetivo;
	private String qualificacao;
	private String anotacoes;
	private String formacao;		
	private String experiencia;	
	private String treinamento;	
	private String certificacao;
	private Date dataAdmissao;
	private String horaEntrada;
	private String horaSaida;
	private FuncionarioDto funcionarioDto;
	
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getFormacao() {
		return formacao;
	}
	
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	
	public String getObjetivo() {
		return objetivo;
	}
	
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	public String getAnotacoes() {
		return anotacoes;
	}
	
	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
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
		
	public FuncionarioDto getFuncionarioDto() {
		return funcionarioDto;
	}
	
	public void setFuncionarioDto(FuncionarioDto funcionarioDto) {
		this.funcionarioDto = funcionarioDto;
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

	public static ProfileDto fromProfileToDto(Profile profile) {
		
		ProfileDto dto = new ProfileDto();
		
		dto.setUid(profile.getUid());
		dto.setObjetivo(profile.getObjetivo());
		dto.setFormacao(profile.getFormacao());		
		dto.setAnotacoes(profile.getAnotacoes());
		dto.setQualificacao(profile.getQualificacao());
		dto.setExperiencia(profile.getExperiencia());
		dto.setTreinamento(profile.getTreinamento());
		dto.setCertificacao(profile.getCertificacao());
		dto.setDataAdmissao(profile.getDataAdmissao());
		dto.setHoraEntrada(profile.getHoraEntrada());
		dto.setHoraSaida(profile.getHoraSaida());
		
		if (profile.getFuncionario() != null) {
			
			Funcionario funcionario = new Funcionario();
			funcionario = profile.getFuncionario();
			
			dto.setFuncionarioDto(FuncionarioDto.fromFuncionarioToDto(funcionario));
		}
			
		return dto;
	}

	public static List<ProfileDto> fromProfileToListDto(List<Profile> list) {
		
		List<ProfileDto> returnList = new ArrayList<ProfileDto>();
		
		for (Profile profile   : list) {
			ProfileDto dto = new ProfileDto();
			
			dto.setUid(profile.getUid());
			dto.setObjetivo(profile.getObjetivo());
			dto.setFormacao(profile.getFormacao());		
			dto.setAnotacoes(profile.getAnotacoes());
			dto.setQualificacao(profile.getQualificacao());
			dto.setExperiencia(profile.getExperiencia());
			dto.setTreinamento(profile.getTreinamento());
			dto.setCertificacao(profile.getCertificacao());
			
			if (profile.getFuncionario() != null) {
				
				Funcionario funcionario = new Funcionario();
				funcionario = profile.getFuncionario();
				
				dto.setFuncionarioDto(FuncionarioDto.fromFuncionarioToDto(funcionario));
			}
			
			returnList.add(dto);
		}
		
		return returnList;
	}
}