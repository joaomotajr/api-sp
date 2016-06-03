package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.RatingFerramenta;

public class RatingFerramentaDto {

	private Long uid;
	private Integer rating;
	private boolean conhecer;
	private boolean multiplicador;
	private FerramentaDto ferramenta;
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

	public FerramentaDto getFerramenta() {
		return ferramenta;
	}

	public void setFerramenta(FerramentaDto ferramenta) {
		this.ferramenta = ferramenta;
	}

	public FuncionarioDto getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDto funcionario) {
		this.funcionario = funcionario;
	}

	public static RatingFerramentaDto fromRatingFerramentaDto(RatingFerramenta ratingFerramenta) {
		RatingFerramentaDto dto = new RatingFerramentaDto();
		dto.setUid(ratingFerramenta.getUid());
		dto.setRating(ratingFerramenta.getRating());
		dto.setConhecer(ratingFerramenta.isConhecer());
		dto.setMultiplicador(ratingFerramenta.isMultiplicador());
		dto.setFerramenta(FerramentaDto.fromFerramentaToDto(ratingFerramenta.getFerramenta()));
		dto.setFuncionario(FuncionarioDto.fromFuncionarioToDto(ratingFerramenta.getFuncionario()));
		return dto;
	}
	
	public static List<RatingFerramentaDto> fromRatingFerramentaToListDto(List<RatingFerramenta> ratingFerramentas) {
		List<RatingFerramentaDto> returnList = new ArrayList<RatingFerramentaDto>();
		for (RatingFerramenta ratingFerramenta : ratingFerramentas) {
			RatingFerramentaDto dto = new RatingFerramentaDto();
			dto.setUid(ratingFerramenta.getUid());
			dto.setRating(ratingFerramenta.getRating());
			dto.setConhecer(ratingFerramenta.isConhecer());
			dto.setMultiplicador(ratingFerramenta.isMultiplicador());
			dto.setFerramenta(FerramentaDto.fromFerramentaToDto(ratingFerramenta.getFerramenta()));
			dto.setFuncionario(FuncionarioDto.fromFuncionarioToDto(ratingFerramenta.getFuncionario()));
			returnList.add(dto);
		}
		return returnList;
	}	

}
