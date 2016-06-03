package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.CategoriaTecnologica;

public class CategoriaTecnologicaDto {

	private Long uid;
	private String nome;

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
	
	public static CategoriaTecnologicaDto fromCategoriaTecnologicaToDto(CategoriaTecnologica categoriaTecnologica){
		CategoriaTecnologicaDto dto = new CategoriaTecnologicaDto();
		dto.setUid(categoriaTecnologica.getUid());
		dto.setNome(categoriaTecnologica.getNome());
		return dto;
	}

	public static List<CategoriaTecnologicaDto> fromCategoriaTecnologicaToListDto(List<CategoriaTecnologica> lista) {
		List<CategoriaTecnologicaDto> list = new ArrayList<CategoriaTecnologicaDto>();
		for (CategoriaTecnologica categoriaTecnologica : lista) {
			CategoriaTecnologicaDto dto = new CategoriaTecnologicaDto();
			dto.setUid(categoriaTecnologica.getUid());
			dto.setNome(categoriaTecnologica.getNome());
			list.add(dto);
		}
		return list;
	}
}
