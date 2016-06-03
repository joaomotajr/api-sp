package br.gov.caixa.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BuscaKey implements Serializable {

	@Column(name = "disciplina_id")
	private Long disciplina_id;

	@Column(name = "funcionario_id")
	private Long funcionario_id;

	public Long getDisciplina_id() {
		return disciplina_id;
	}

	public void setDisciplina_id(Long disciplina_id) {
		this.disciplina_id = disciplina_id;
	}

	public Long getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Long funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((disciplina_id == null) ? 0 : disciplina_id.hashCode());
		result = prime * result
				+ ((funcionario_id == null) ? 0 : funcionario_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuscaKey other = (BuscaKey) obj;
		if (disciplina_id == null) {
			if (other.disciplina_id != null)
				return false;
		} else if (!disciplina_id.equals(other.disciplina_id))
			return false;
		if (funcionario_id == null) {
			if (other.funcionario_id != null)
				return false;
		} else if (!funcionario_id.equals(other.funcionario_id))
			return false;
		return true;
	}
}
