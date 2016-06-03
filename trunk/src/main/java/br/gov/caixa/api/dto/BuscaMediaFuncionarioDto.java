package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.BuscaMediaFuncionario;

public class BuscaMediaFuncionarioDto {

	private Long disciplina_id;
	private Long funcionario_id;
	private Long multiplicador;
	private Long conhecer;
	private Long total_rating;
	private Double media_rating;
	private Long total_atividades;
	private String nome;
	private String matricula;

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

	public static BuscaMediaFuncionarioDto fromBuscaMediaFuncionarioToDto(BuscaMediaFuncionario media) {
		BuscaMediaFuncionarioDto dto = new BuscaMediaFuncionarioDto();
		dto.setDisciplina_id(media.getKey().getDisciplina_id());
		dto.setFuncionario_id(media.getKey().getFuncionario_id());
		dto.setMultiplicador(media.getMultiplicador());
		dto.setConhecer(media.getConhecer());
		dto.setTotal_rating(media.getTotal_rating());
		dto.setMedia_rating(media.getMedia_rating());
		dto.setTotal_atividades(media.getTotal_atividades());
		dto.setNome(media.getNome());
		dto.setMatricula(media.getMatricula());
		return dto;
	}
	
	public static List<BuscaMediaFuncionarioDto> fromBuscaMediaFuncionarioToListDto(List<BuscaMediaFuncionario> lista){
		List<BuscaMediaFuncionarioDto> listaRetorno = new ArrayList<BuscaMediaFuncionarioDto>();
		for (BuscaMediaFuncionario media : lista) {
			BuscaMediaFuncionarioDto dto = new BuscaMediaFuncionarioDto();
			dto.setDisciplina_id(media.getKey().getDisciplina_id());
			dto.setFuncionario_id(media.getKey().getFuncionario_id());
			dto.setMultiplicador(media.getMultiplicador());
			dto.setConhecer(media.getConhecer());
			dto.setTotal_rating(media.getTotal_rating());
			dto.setMedia_rating(media.getMedia_rating());
			dto.setTotal_atividades(media.getTotal_atividades());
			dto.setNome(media.getNome());
			dto.setMatricula(media.getMatricula());
			listaRetorno.add(dto);
		}
		return listaRetorno;
	}
}