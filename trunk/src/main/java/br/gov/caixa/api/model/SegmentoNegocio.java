package br.gov.caixa.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.caixa.api.dto.SegmentoNegocioDto;

@Entity
@Table(name = "segmento_negocio")
public class SegmentoNegocio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "DESCRICAO")
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
	
	public static SegmentoNegocio fromDtoToSegmentoNegocio(SegmentoNegocioDto segmentoNegocioDto) {
		
		SegmentoNegocio segmentoNegocio = new SegmentoNegocio();
		
		segmentoNegocio.setUid(segmentoNegocioDto.getUid());
		segmentoNegocio.setDescricao(segmentoNegocioDto.getDescricao());
		
		return segmentoNegocio;
	}
}
