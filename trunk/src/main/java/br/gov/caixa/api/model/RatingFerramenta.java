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

import br.gov.caixa.api.dto.RatingFerramentaDto;

@Entity
@Table(name = "rating_ferramenta")
public class RatingFerramenta {

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
	@JoinColumn(name = "FERRAMENTA_ID")
	private Ferramenta ferramenta;

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

	public Ferramenta getFerramenta() {
		return ferramenta;
	}

	public void setFerramenta(Ferramenta ferramenta) {
		this.ferramenta = ferramenta;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	public static RatingFerramenta fromDtoToRatingFerramenta(RatingFerramentaDto ratingFerramentaDto) {
		RatingFerramenta ratingFerramenta = new RatingFerramenta();
		ratingFerramenta.setUid(ratingFerramentaDto.getUid());
		ratingFerramenta.setRating(ratingFerramentaDto.getRating());
		ratingFerramenta.setConhecer(ratingFerramentaDto.isConhecer());
		ratingFerramenta.setMultiplicador(ratingFerramentaDto.isMultiplicador());
		ratingFerramenta.setFerramenta(Ferramenta.fromDtoToFerramenta(ratingFerramentaDto.getFerramenta()));
		ratingFerramenta.setFuncionario(Funcionario.fromDtoToFuncionario(ratingFerramentaDto.getFuncionario()));
		return ratingFerramenta;
	}
	
	public static List<RatingFerramenta> fromFerramentaToListRatingFerramenta(List<Ferramenta> ferramentas, Funcionario funcionario) {
		List<RatingFerramenta> returnList = new ArrayList<RatingFerramenta>();
		for (Ferramenta ferramenta : ferramentas) {
			RatingFerramenta ratingFerramenta = new RatingFerramenta();
			ratingFerramenta.setConhecer(false);
			ratingFerramenta.setMultiplicador(false);
			ratingFerramenta.setFerramenta(ferramenta);
			ratingFerramenta.setRating(Integer.valueOf(0));
			ratingFerramenta.setFuncionario(funcionario);
			returnList.add(ratingFerramenta);
		}
		return returnList;
	}	
}
