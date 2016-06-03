package br.gov.caixa.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.caixa.api.dto.FuncionarioDto;
import br.gov.caixa.api.dto.SiappDto;

@Entity
@Table(name = "siapp")
public class Siapp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
		
	@Column(name = "SISTEMA", nullable = false)
	private String sistema;
	
	@Column(name = "CARTEIRA", nullable = false)
	private String carteira;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "CODIGO", nullable = false)
	private Long codigo;	

	@Column(name = "COORDENACAO_PROJETO", nullable = false)
	private String coordenacaoProjeto;
	
	@Column(name = "COORDENACAO_TI", nullable = false)
	private String coordenacaoTi;
			
	@OneToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "siapp_funcionario", joinColumns = @JoinColumn(name = "SIAPP_ID", referencedColumnName = "UID"), 
								inverseJoinColumns = @JoinColumn(name = "FUNCIONARIO_ID", referencedColumnName = "UID"))
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
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
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Siapp fromDtoToSiapp(SiappDto siappDto) {
		Siapp siapp = new Siapp();
		
		siapp.setUid(siappDto.getUid());
		siapp.setSistema(siappDto.getSistema());
		siapp.setCarteira(siappDto.getCarteira());
		siapp.setDescricao(siappDto.getDescricao());
		siapp.setCodigo(siappDto.getCodigo());
		siapp.setCoordenacaoProjeto(siappDto.getCoordenacaoProjeto());
		siapp.setCoordenacaoTi(siappDto.getCoordenacaoTi());
		
		if(siappDto.getFuncionarios() != null){
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			for (FuncionarioDto dto : siappDto.getFuncionarios()) {
				Funcionario funcionario = Funcionario.fromDtoToFuncionario(dto);
				funcionarios.add(funcionario);
			}
			siapp.setFuncionarios(funcionarios);
		}
		return siapp;
	}
}
