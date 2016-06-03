package br.gov.caixa.api.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "busca_funcionario_medias")
public class BuscaMediaFuncionario {

	@EmbeddedId
	private BuscaKey key;

	@Column(name = "multiplicador")
	private Long multiplicador;

	@Column(name = "conhecer")
	private Long conhecer;

	@Column(name = "total_rating")
	private Long total_rating;

	@Column(name = "media_rating")
	private Double media_rating;

	@Column(name = "total_atividades")
	private Long total_atividades;

	@Column(name = "nome")
	private String nome;

	@Column(name = "matricula")
	private String matricula;

	public BuscaKey getKey() {
		return key;
	}

	public void setKey(BuscaKey key) {
		this.key = key;
	}

	public Long getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(Long multiplicador) {
		this.multiplicador = multiplicador;
	}

	public Long getConhecer() {
		return conhecer;
	}

	public void setConhecer(Long conhecer) {
		this.conhecer = conhecer;
	}

	public Long getTotal_rating() {
		return total_rating;
	}

	public void setTotal_rating(Long total_rating) {
		this.total_rating = total_rating;
	}

	public Double getMedia_rating() {
		return media_rating;
	}

	public void setMedia_rating(Double media_rating) {
		this.media_rating = media_rating;
	}

	public Long getTotal_atividades() {
		return total_atividades;
	}

	public void setTotal_atividades(Long total_atividades) {
		this.total_atividades = total_atividades;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}