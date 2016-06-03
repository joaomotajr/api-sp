package br.gov.caixa.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.caixa.api.dto.DisciplinaDto;

@Entity
@Table(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	@Column(name = "nome")
	private String nome;

	@Column(name = "METODOLOGIA", columnDefinition = "int default 1")
	private Metodologia metodologia;

	@Enumerated(EnumType.ORDINAL)
	private Metodologia Metodologia() {
		return metodologia;
	}

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

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public static Disciplina fromDtoToDisciplina(DisciplinaDto disciplinaDto) {
		Disciplina disciplina = new Disciplina();
		disciplina.setUid(disciplinaDto.getUid());
		disciplina.setNome(disciplinaDto.getNome());
		disciplina.setMetodologia(disciplinaDto.getMetodologia());
		return disciplina;
	}
}
