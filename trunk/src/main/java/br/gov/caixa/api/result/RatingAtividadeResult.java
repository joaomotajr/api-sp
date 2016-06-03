package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.RatingAtividadeDto;

public class RatingAtividadeResult extends BasicResult {

	private List<RatingAtividadeDto> list;
	private RatingAtividadeDto ratingAtividade;

	public List<RatingAtividadeDto> getList() {
		return list;
	}

	public void setList(List<RatingAtividadeDto> list) {
		this.list = list;
	}

	public RatingAtividadeDto getRatingAtividade() {
		return ratingAtividade;
	}

	public void setRatingAtividade(RatingAtividadeDto ratingAtividade) {
		this.ratingAtividade = ratingAtividade;
	}

}
