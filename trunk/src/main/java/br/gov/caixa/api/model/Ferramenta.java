package br.gov.caixa.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.caixa.api.dto.FerramentaDto;

@Entity
@Table(name = "ferramenta")
public class Ferramenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	@Column(name = "nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORIA_TECNOLOGICA_ID")
	private CategoriaTecnologica categoriaTecnologica;

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

	public CategoriaTecnologica getCategoriaTecnologica() {
		return categoriaTecnologica;
	}

	public void setCategoriaTecnologica(CategoriaTecnologica categoriaTecnologica) {
		this.categoriaTecnologica = categoriaTecnologica;
	}
	
	public static Ferramenta fromDtoToFerramenta(FerramentaDto dto){
		Ferramenta ferramenta = new Ferramenta();
		ferramenta.setUid(dto.getUid());
		ferramenta.setNome(dto.getNome());
		if(dto.getCategoriaTecnologica() != null){
			ferramenta.setCategoriaTecnologica(CategoriaTecnologica.fromDtoToCategoriaTecnologica(dto.getCategoriaTecnologica()));
		}
		return ferramenta;
	}
}
