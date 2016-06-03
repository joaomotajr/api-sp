package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.CategoriaTecnologicaDto;

public class CategoriaTecnologicaResult extends BasicResult {

	private List<CategoriaTecnologicaDto> list;
	private CategoriaTecnologicaDto categoriaTecnologicaDto;

	public List<CategoriaTecnologicaDto> getList() {
		return list;
	}

	public void setList(List<CategoriaTecnologicaDto> list) {
		this.list = list;
	}

	public CategoriaTecnologicaDto getCategoriaTecnologicaDto() {
		return categoriaTecnologicaDto;
	}

	public void setCategoriaTecnologicaDto(
			CategoriaTecnologicaDto categoriaTecnologicaDto) {
		this.categoriaTecnologicaDto = categoriaTecnologicaDto;
	}

}
