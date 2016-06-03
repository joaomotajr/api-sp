package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.gov.caixa.api.model.Auditoria;
import br.gov.caixa.api.model.Coordenacao;
import br.gov.caixa.api.model.Funcionario;

public class AuditoriaDto {
	
	private Long uid;	
	private String detalhe;	
	private String acao;
	private CoordenacaoDto coordenacaoDto;	
	private FuncionarioDto funcionarioDto;
	
	private Date data;
		
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}	
	
	public CoordenacaoDto getCoordenacaoDto() {
		return coordenacaoDto;
	}

	public void setCoordenacaoDto(CoordenacaoDto coordenacaoDto) {
		this.coordenacaoDto = coordenacaoDto;
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
	
	public FuncionarioDto getFuncionarioDto() {
		return funcionarioDto;
	}

	public void setFuncionarioDto(FuncionarioDto funcionarioDto) {
		this.funcionarioDto = funcionarioDto;
	}
	
	public static AuditoriaDto fromAuditoriaToDto(Auditoria auditoria) {
		
		AuditoriaDto dto = new AuditoriaDto();
		
		dto.setUid(auditoria.getUid());		
		dto.setDetalhe(auditoria.getDetalhe());
		
		if (auditoria.getCoordenacao() != null) {
			
			Coordenacao coordenacao = new Coordenacao();
			coordenacao = auditoria.getCoordenacao();			
			
			dto.setCoordenacaoDto(CoordenacaoDto.fromCoordenacaoToDto(coordenacao));
		}
		
		if (auditoria.getFuncionario() != null) {
			
			Funcionario funcionario = new Funcionario();
			funcionario = auditoria.getFuncionario();
			
			dto.setFuncionarioDto(FuncionarioDto.fromFuncionarioToDto(funcionario));
		}
		
		dto.setAcao(auditoria.getAcao());		
		dto.setData(auditoria.getData());
		
		return dto;
	}
	
	public static List<AuditoriaDto> fromAuditoriaToListDto(List<Auditoria> list) {
		
		List<AuditoriaDto> returnList = new ArrayList<AuditoriaDto>();
		
		for (Auditoria auditoria   : list) {
			AuditoriaDto dto = new AuditoriaDto();
		
			dto.setUid(auditoria.getUid());			
			dto.setDetalhe(auditoria.getDetalhe());	
			
			if (auditoria.getCoordenacao() != null) {
				
				Coordenacao coordenacao = new Coordenacao();
				coordenacao = auditoria.getCoordenacao();			
				
				dto.setCoordenacaoDto(CoordenacaoDto.fromCoordenacaoToDto(coordenacao));
			}
			
			if (auditoria.getFuncionario() != null) {
				
				Funcionario funcionario = new Funcionario();
				funcionario = auditoria.getFuncionario();
				
				dto.setFuncionarioDto(FuncionarioDto.fromFuncionarioToDto(funcionario));
			}
			
			dto.setAcao(auditoria.getAcao());			
			dto.setData(auditoria.getData());
				
			returnList.add(dto);
		}
		
		return returnList;
	}
}
