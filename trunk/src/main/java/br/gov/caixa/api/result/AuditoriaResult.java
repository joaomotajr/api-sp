package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.AuditoriaDto;

public class AuditoriaResult extends BasicResult {
	private List<AuditoriaDto> list;
	private AuditoriaDto Auditoria;	

	public List<AuditoriaDto> getList() {
		return list;
	}

	public void setList(List<AuditoriaDto> list) {
		this.list = list;
	}

	public AuditoriaDto getAuditoria() {
		return Auditoria;
	}

	public void setAuditoria(AuditoriaDto Auditoria) {
		this.Auditoria = Auditoria;
	}
}
