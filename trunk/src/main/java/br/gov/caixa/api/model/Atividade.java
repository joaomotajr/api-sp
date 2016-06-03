package br.gov.caixa.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.caixa.api.dto.AtividadeDto;

@Entity
@Table(name = "atividade")
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	@Column(name = "nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "DISCIPLINA_ID")
	private Disciplina disciplina;

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

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public static Atividade fromDtoToAtividade(AtividadeDto atividadeDto) {
		Atividade atividade = new Atividade();
		
		atividade.setUid(atividadeDto.getUid());
		atividade.setNome(atividadeDto.getNome());
		
		if(atividadeDto.getDisciplina() != null){
			atividade.setDisciplina(Disciplina.fromDtoToDisciplina(atividadeDto.getDisciplina()));
		}
		
		return atividade;
	}
}
