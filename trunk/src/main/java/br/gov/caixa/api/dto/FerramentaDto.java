package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.Ferramenta;

public class FerramentaDto {

	private Long uid;
	private String nome;
	private CategoriaTecnologicaDto categoriaTecnologica;

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

	public CategoriaTecnologicaDto getCategoriaTecnologica() {
		return categoriaTecnologica;
	}

	public void setCategoriaTecnologica(
			CategoriaTecnologicaDto categoriaTecnologica) {
		this.categoriaTecnologica = categoriaTecnologica;
	}
	
	public static FerramentaDto fromFerramentaToDto(Ferramenta ferramenta){
		FerramentaDto dto = new FerramentaDto();
		dto.setUid(ferramenta.getUid());
		dto.setNome(ferramenta.getNome());
		if(ferramenta.getCategoriaTecnologica() != null){
			dto.setCategoriaTecnologica(CategoriaTecnologicaDto.fromCategoriaTecnologicaToDto(ferramenta.getCategoriaTecnologica()));
		}
		return dto;
	}

	public static List<FerramentaDto> fromFerramentaToListDto(List<Ferramenta> lista) {
		List<FerramentaDto> list = new ArrayList<FerramentaDto>();
		for (Ferramenta ferramenta : lista) {
			FerramentaDto dto = new FerramentaDto();
			dto.setUid(ferramenta.getUid());
			dto.setNome(ferramenta.getNome());
			dto.setCategoriaTecnologica(CategoriaTecnologicaDto.fromCategoriaTecnologicaToDto(ferramenta.getCategoriaTecnologica()));
			list.add(dto);
		}
		return list;
	}
}
