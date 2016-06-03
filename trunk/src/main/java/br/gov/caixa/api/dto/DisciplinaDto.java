package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.Disciplina;
import br.gov.caixa.api.model.Metodologia;

public class DisciplinaDto {

	private Long uid;
	private String nome;
	private Metodologia metodologia;

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

	public static DisciplinaDto fromDisciplinaToDto(Disciplina disciplina) {
		DisciplinaDto dto = new DisciplinaDto();
		dto.setUid(disciplina.getUid());
		dto.setNome(disciplina.getNome());
		dto.setMetodologia(disciplina.getMetodologia());
		return dto;
	}
	
	public static List<DisciplinaDto> fromDisciplinaToListDto(List<Disciplina> list) {
		
		List<DisciplinaDto> returnList = new ArrayList<DisciplinaDto>();
		
		for (Disciplina disciplina   : list) {
			
			DisciplinaDto dto = new DisciplinaDto();
			
			dto.setUid(disciplina.getUid());
			dto.setNome(disciplina.getNome());
			dto.setMetodologia(disciplina.getMetodologia());
			
			returnList.add(dto);
		}
		
		return returnList;
		
	}
}
