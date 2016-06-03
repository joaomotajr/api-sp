package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.RatingAtividade;

public class RatingAtividadeDto {

	private Long uid;
	private Integer rating;
	private boolean conhecer;
	private boolean multiplicador;
	private AtividadeDto atividade;
	private FuncionarioDto funcionario;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public boolean isConhecer() {
		return conhecer;
	}

	public void setConhecer(boolean conhecer) {
		this.conhecer = conhecer;
	}

	public boolean isMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(boolean multiplicador) {
		this.multiplicador = multiplicador;
	}
	
	public AtividadeDto getAtividade() {
		return atividade;
	}

	public void setAtividade(AtividadeDto atividade) {
		this.atividade = atividade;
	}

	public FuncionarioDto getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDto funcionario) {
		this.funcionario = funcionario;
	}

	public static RatingAtividadeDto fromRatingAtividadeDto(RatingAtividade ratingAtvidade) {
		RatingAtividadeDto dto = new RatingAtividadeDto();
		dto.setUid(ratingAtvidade.getUid());
		dto.setRating(ratingAtvidade.getRating());
		dto.setConhecer(ratingAtvidade.isConhecer());
		dto.setMultiplicador(ratingAtvidade.isMultiplicador());
		dto.setAtividade(AtividadeDto.fromAtividadeToDto(ratingAtvidade.getAtividade()));
		dto.setFuncionario(FuncionarioDto.fromFuncionarioToDto(ratingAtvidade.getFuncionario()));
		return dto;
	}
	
	public static List<RatingAtividadeDto> fromRatingAtividadeToListDto(List<RatingAtividade> ratingAtividades) {

		List<RatingAtividadeDto> returnList = new ArrayList<RatingAtividadeDto>();
		for (RatingAtividade ratingAtividade : ratingAtividades) {
			RatingAtividadeDto dto = new RatingAtividadeDto();
			dto.setUid(ratingAtividade.getUid());
			dto.setRating(ratingAtividade.getRating());
			dto.setConhecer(ratingAtividade.isConhecer());
			dto.setMultiplicador(ratingAtividade.isMultiplicador());
			dto.setAtividade(AtividadeDto.fromAtividadeToDto(ratingAtividade.getAtividade()));
			dto.setFuncionario(FuncionarioDto.fromFuncionarioToDto(ratingAtividade.getFuncionario()));
			returnList.add(dto);
		}
		return returnList;
	}	

}
