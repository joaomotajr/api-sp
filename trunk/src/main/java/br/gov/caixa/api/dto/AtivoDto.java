package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.Ativo;
import br.gov.caixa.api.model.TipoAtivo;

public class AtivoDto {

	private Long uid;
	private String nome;
	private String descricao;
	private TipoAtivo tipoAtivo;
	private CoordenacaoDto coordenacaoDto;
	private List<FuncionarioDto> funcionarios;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoAtivo getTipoAtivo() {
		return tipoAtivo;
	}

	public void setTipoAtivo(TipoAtivo tipoAtivo) {
		this.tipoAtivo = tipoAtivo;
	}
	
	public CoordenacaoDto getCoordenacaoDto() {
		return coordenacaoDto;
	}

	public void setCoordenacaoDto(CoordenacaoDto coordenacaoDto) {
		this.coordenacaoDto = coordenacaoDto;
	}

	public List<FuncionarioDto> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioDto> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public static List<AtivoDto> fromAtivoToListDto(List<Ativo> list) {
		
		List<AtivoDto> returnList = new ArrayList<AtivoDto>();
		
		for (Ativo ativo   : list) {
			
			AtivoDto dto = new AtivoDto();
			
			dto.setUid(ativo.getUid());
			dto.setNome(ativo.getNome());
			dto.setDescricao(ativo.getDescricao());
			dto.setTipoAtivo(ativo.getTipoAtivo());
			
			if(ativo.getCoordenacao() != null){
				CoordenacaoDto coordenacaoDto = CoordenacaoDto.fromCoordenacaoToDto(ativo.getCoordenacao());
				dto.setCoordenacaoDto(coordenacaoDto);
			}
			
			if(ativo.getFuncionarios() != null){
				List<FuncionarioDto> funcionarios = FuncionarioDto.fromFuncionarioToListDto(ativo.getFuncionarios());
				dto.setFuncionarios(funcionarios);
			}
			
			returnList.add(dto);
		}
		
		return returnList;
		
	}
}
