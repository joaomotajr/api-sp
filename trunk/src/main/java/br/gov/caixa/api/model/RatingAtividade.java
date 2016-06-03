package br.gov.caixa.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.caixa.api.dto.RatingAtividadeDto;

@Entity
@Table(name = "rating_atividade")
public class RatingAtividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "conhecer", columnDefinition = "boolean default false")
	private boolean conhecer;

	@Column(name = "multiplicador", columnDefinition = "boolean default false")
	private boolean multiplicador;

	@ManyToOne
	@JoinColumn(name = "ATIVIDADE_ID")
	private Atividade atividade;

	@ManyToOne
	@JoinColumn(name = "FUNCIONARIO_ID")
	private Funcionario funcionario;

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

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public static RatingAtividade fromDtoToRatingAtividade(RatingAtividadeDto ratingAtividadeDto) {
		RatingAtividade ratingAtividade = new RatingAtividade();
		ratingAtividade.setUid(ratingAtividadeDto.getUid());
		ratingAtividade.setRating(ratingAtividadeDto.getRating());
		ratingAtividade.setConhecer(ratingAtividadeDto.isConhecer());
		ratingAtividade.setMultiplicador(ratingAtividadeDto.isMultiplicador());
		ratingAtividade.setAtividade(Atividade.fromDtoToAtividade(ratingAtividadeDto.getAtividade()));
		ratingAtividade.setFuncionario(Funcionario.fromDtoToFuncionario(ratingAtividadeDto.getFuncionario()));
		return ratingAtividade;
	}
	
	public static List<RatingAtividade> fromAtividadetoListRatingAtividade(List<Atividade> atividades, Funcionario funcionario) {
		List<RatingAtividade> returnList = new ArrayList<RatingAtividade>();
		for (Atividade atividade : atividades) {
			RatingAtividade ratingAtividade = new RatingAtividade();
			ratingAtividade.setConhecer(false);
			ratingAtividade.setMultiplicador(false);
			ratingAtividade.setAtividade(atividade);
			ratingAtividade.setRating(Integer.valueOf(0));
			ratingAtividade.setFuncionario(funcionario);
			returnList.add(ratingAtividade);
		}
		return returnList;
	}	
}
