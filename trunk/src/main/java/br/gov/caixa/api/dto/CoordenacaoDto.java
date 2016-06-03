package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.Coordenacao;

public class CoordenacaoDto {

	public CoordenacaoDto() {

	}

	private Long uid;
	private CoordenacaoDto parent;
	private String nome;
	private String url;
	private String grupo;
	private String tipo;
	private String descricao;
	private SegmentoNegocioDto segmentoNegocio;
	private String titulo;	
	private Long coordenadorId;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public CoordenacaoDto getParent() {
		return parent;
	}

	public void setParent(CoordenacaoDto parent) {
		this.parent = parent;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
	public Long getCoordenadorId() {
		return coordenadorId;
	}

	public void setCoordenadorId(Long coordenadorId) {
		this.coordenadorId = coordenadorId;
	}

	public SegmentoNegocioDto getSegmentoNegocio() {
		return segmentoNegocio;
	}

	public void setSegmentoNegocio(SegmentoNegocioDto segmentoNegocio) {
		this.segmentoNegocio = segmentoNegocio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public static CoordenacaoDto fromCoordenacaoToDto(Coordenacao coordenacao) {
		
		CoordenacaoDto coordenacaoDto = new CoordenacaoDto();
		
		coordenacaoDto.setUid(coordenacao.getUid());
		coordenacaoDto.setNome(coordenacao.getNome());
		coordenacaoDto.setUrl(coordenacao.getUrl());
		coordenacaoDto.setGrupo(coordenacao.getGrupo());
		coordenacaoDto.setTipo(coordenacao.getTipo());
		coordenacaoDto.setDescricao(coordenacao.getDescricao());
		coordenacaoDto.setTitulo(coordenacao.getTitulo());
		coordenacaoDto.setCoordenadorId(coordenacao.getCoordenadorId());	
				
		if(coordenacao.getParent() != null){
			CoordenacaoDto parent = fromCoordenacaoToDto(coordenacao.getParent());
			coordenacaoDto.setParent(parent);
		}		
		
		return coordenacaoDto;
	}		
	
	public static List<CoordenacaoDto> fromCoordenacaoToListDto(List<Coordenacao> list) {
		
		List<CoordenacaoDto> returnList = new ArrayList<CoordenacaoDto>();
		
		for (Coordenacao coordenacao   : list) {
			CoordenacaoDto dto = new CoordenacaoDto();
			
			dto.setUid(coordenacao.getUid());
			dto.setNome(coordenacao.getNome());			
			dto.setUrl(coordenacao.getUrl());
			dto.setGrupo(coordenacao.getGrupo());
			dto.setTipo(coordenacao.getTipo());
			dto.setDescricao(coordenacao.getDescricao());
			dto.setTitulo(coordenacao.getTitulo());
			dto.setCoordenadorId(coordenacao.getCoordenadorId());		
			
			if (coordenacao.getParent() != null) {
				CoordenacaoDto parent = fromCoordenacaoToDto(coordenacao.getParent());
				dto.setParent(parent);
			}
			
			if(coordenacao.getSegmentoNegocio() != null){
				SegmentoNegocioDto segmentoNegocio = SegmentoNegocioDto.fromSegmentoNegocioToDto(coordenacao.getSegmentoNegocio());
				dto.setSegmentoNegocio(segmentoNegocio);
			}
			
			returnList.add(dto);
		}
		
		return returnList;
	}
}
