package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.DisciplinaDto;

public class DisciplinaResult extends BasicResult {
	private List<DisciplinaDto> list;
	private DisciplinaDto disciplina;

	public List<DisciplinaDto> getList() {
		return list;
	}

	public void setList(List<DisciplinaDto> list) {
		this.list = list;
	}

	public DisciplinaDto getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(DisciplinaDto disciplina) {
		this.disciplina = disciplina;
	}
}
