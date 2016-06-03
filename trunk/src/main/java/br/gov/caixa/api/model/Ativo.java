package br.gov.caixa.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.caixa.api.dto.AtivoDto;
import br.gov.caixa.api.dto.FuncionarioDto;

@Entity
@Table(name = "ativos")
public class Ativo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "TIPO_ATIVO", columnDefinition = "int default 0")
	private TipoAtivo tipoAtivo;		

	@Enumerated(EnumType.ORDINAL) 
	private TipoAtivo TipoAtivo() { 
	    return tipoAtivo; 
	}
	
	@ManyToOne
	@JoinColumn(name="COORDENACAO_ID")
	private Coordenacao coordenacao;
	
	@OneToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "ativo_funcionario", joinColumns = @JoinColumn(name = "ATIVO_ID", referencedColumnName = "UID"), 
								inverseJoinColumns = @JoinColumn(name = "FUNCIONARIO_ID", referencedColumnName = "UID"))
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
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
		if (tipoAtivo == null ) {			
			this.tipoAtivo = TipoAtivo.FERRAMENTAS;
		}	
		else { 
			this.tipoAtivo = tipoAtivo;
		}
	}

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public static Ativo fromDtoToAtivo(AtivoDto ativoDto) {
		
		Ativo ativo = new Ativo();
		
		ativo.setUid(ativoDto.getUid());
		ativo.setNome(ativoDto.getNome());
		ativo.setDescricao(ativoDto.getDescricao());
		ativo.setTipoAtivo(ativoDto.getTipoAtivo());
		
		if(ativoDto.getCoordenacaoDto() != null){
			Coordenacao coordenacao = Coordenacao.fromDtoToCoordenacao(ativoDto.getCoordenacaoDto());
			ativo.setCoordenacao(coordenacao);
		}
		
		if(ativoDto.getFuncionarios() != null){
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			for (FuncionarioDto dto : ativoDto.getFuncionarios()) {
				Funcionario funcionario = Funcionario.fromDtoToFuncionario(dto);
				funcionarios.add(funcionario);
			}
			ativo.setFuncionarios(funcionarios);
		}
		
		return ativo;
	}

}

