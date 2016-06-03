package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.RatingFerramentaDto;

public class RatingFerramentaResult extends BasicResult {

	private List<RatingFerramentaDto> list;
	private RatingFerramentaDto ratingFerramenta;

	public List<RatingFerramentaDto> getList() {
		return list;
	}

	public void setList(List<RatingFerramentaDto> list) {
		this.list = list;
	}

	public RatingFerramentaDto getRatingFerramenta() {
		return ratingFerramenta;
	}

	public void setRatingFerramenta(RatingFerramentaDto ratingFerramenta) {
		this.ratingFerramenta = ratingFerramenta;
	}

}
