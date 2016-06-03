package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.Siapp;

public class SiappDto {
	
	private Long uid;	
	private String sistema;
	private String carteira;
	private String descricao;
	private Long codigo;
	private String coordenacaoProjeto;
	private String coordenacaoTi;	

	private List<FuncionarioDto> funcionarios = new ArrayList<FuncionarioDto>();
	
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getSistema() {
		return sistema;
	}
	
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
	public String getCarteira() {
		return carteira;
	}
	
	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getCoordenacaoProjeto() {
		return coordenacaoProjeto;
	}
	
	public void setCoordenacaoProjeto(String coordenacaoProjeto) {
		this.coordenacaoProjeto = coordenacaoProjeto;
	}
	
	public String getCoordenacaoTi() {
		return coordenacaoTi;
	}

	public void setCoordenacaoTi(String coordenacaoTi) {
		this.coordenacaoTi = coordenacaoTi;
	}

	public List<FuncionarioDto> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioDto> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public static SiappDto fromSiappToDto(Siapp siapp) {
		
		SiappDto dto = new SiappDto();
		
		dto.setUid(siapp.getUid());
		dto.setSistema(siapp.getSistema());
		dto.setCarteira(siapp.getCarteira());
		dto.setDescricao(siapp.getDescricao());
		dto.setCodigo(siapp.getCodigo());
		dto.setCoordenacaoProjeto(siapp.getCoordenacaoProjeto());
		dto.setCoordenacaoTi(siapp.getCoordenacaoTi());
		
		if(siapp.getFuncionarios() != null){
			List<FuncionarioDto> funcionarios = new ArrayList<FuncionarioDto>(); 
			for (Funcionario funcionario : siapp.getFuncionarios()) {
				FuncionarioDto funcionarioDto = FuncionarioDto.fromFuncionarioToDto(funcionario);
				funcionarios.add(funcionarioDto);
			}
			dto.setFuncionarios(funcionarios);
		}
			
		return dto;
	}
	
	public static List<SiappDto> fromSiappToListDto(List<Siapp> list) {
		
		List<SiappDto> returnList = new ArrayList<SiappDto>();
		
		for (Siapp siapp   : list) {
			SiappDto dto = new SiappDto();
			
			dto.setUid(siapp.getUid());
			dto.setSistema(siapp.getSistema());
			dto.setCarteira(siapp.getCarteira());
			dto.setDescricao(siapp.getDescricao());
			dto.setCodigo(siapp.getCodigo());
			dto.setCoordenacaoProjeto(siapp.getCoordenacaoProjeto());
			dto.setCoordenacaoTi(siapp.getCoordenacaoTi());
			
			if(siapp.getFuncionarios() != null){
				List<FuncionarioDto> funcionarios = new ArrayList<FuncionarioDto>(); 
				for (Funcionario funcionario : siapp.getFuncionarios()) {
					FuncionarioDto funcionarioDto = FuncionarioDto.fromFuncionarioToDto(funcionario);
					funcionarios.add(funcionarioDto);
				}
				dto.setFuncionarios(funcionarios);
			}
			
			
			returnList.add(dto);
		}
		
		return returnList;
	}
	
}
