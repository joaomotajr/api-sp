package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.SegmentoNegocio;

public class SegmentoNegocioDto {

	public SegmentoNegocioDto() {

	}

	private Long uid;
	private String descricao;

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

	public static List<SegmentoNegocioDto> fromSegmentoNegocioToListDto(List<SegmentoNegocio> list) {
		
		List<SegmentoNegocioDto> returnList = new ArrayList<SegmentoNegocioDto>();
		
		for (SegmentoNegocio segmentoNegocio : list) {
			SegmentoNegocioDto dto = new SegmentoNegocioDto();
			
			dto.setUid(segmentoNegocio.getUid());
			dto.setDescricao(segmentoNegocio.getDescricao());
			
			returnList.add(dto);
		}
		
		return returnList;
	}
	
	public static SegmentoNegocioDto fromSegmentoNegocioToDto(SegmentoNegocio segmentoNegocio){
		SegmentoNegocioDto dto = new SegmentoNegocioDto();
		dto.setUid(segmentoNegocio.getUid());
		dto.setDescricao(segmentoNegocio.getDescricao());
		return dto;		
	}

}
