package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.Atividade;

public class AtividadeDto {

	private Long uid;
	private String nome;
	private DisciplinaDto disciplina;

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

	public DisciplinaDto getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(DisciplinaDto disciplina) {
		this.disciplina = disciplina;
	}

	public static AtividadeDto fromAtividadeToDto(Atividade atividade) {
		AtividadeDto dto = new AtividadeDto();
		dto.setUid(atividade.getUid());
		dto.setNome(atividade.getNome());
		dto.setDisciplina(DisciplinaDto.fromDisciplinaToDto(atividade.getDisciplina()));
		return dto;
	}

	public static List<AtividadeDto> fromAtividadeToListDto(List<Atividade> atividades) {

		List<AtividadeDto> returnList = new ArrayList<AtividadeDto>();			
		
		for (Atividade atividade : atividades) {
			
			AtividadeDto dto = new AtividadeDto();
			
			dto.setUid(atividade.getUid());
			dto.setNome(atividade.getNome());
			dto.setDisciplina(DisciplinaDto.fromDisciplinaToDto(atividade.getDisciplina()));
			
			returnList.add(dto);
			
		}
		
		return returnList;
	}

}
