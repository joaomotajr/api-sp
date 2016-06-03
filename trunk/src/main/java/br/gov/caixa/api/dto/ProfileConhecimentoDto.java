package br.gov.caixa.api.dto;

public class ProfileConhecimentoDto {
	private Long uid;
	private Long funcionarioUid;	
	private Long ativoUid;
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getFuncionarioUid() {
		return funcionarioUid;
	}
	public void setFuncionarioUid(Long funcionarioUid) {
		this.funcionarioUid = funcionarioUid;
	}
	public Long getAtivoUid() {
		return ativoUid;
	}
	public void setAtivoUid(Long ativoUid) {
		this.ativoUid = ativoUid;
	}
	
	public static ProfileConhecimentoDto fromFuncionarioProfileAtivoToDto(ProfileConhecimentoDto funcionarioProfileAtivoDto) {
		return funcionarioProfileAtivoDto;
	}
}
