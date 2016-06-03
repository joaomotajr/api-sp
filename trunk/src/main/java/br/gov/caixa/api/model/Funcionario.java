package br.gov.caixa.api.model;

import java.util.Date;

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
import javax.persistence.Table;

import br.gov.caixa.api.dto.FuncionarioDto;

@Entity
@Table(name = "funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "MATRICULA", nullable = false)
	private String matricula;

	@Column(name = "CARGO", nullable = true)
	private String cargo;
	
	@Column(name = "DEPTO", nullable = true)
	private String depto;

	@Column(name = "TIPO_FUNCIONARIO", columnDefinition = "int default 0")
	private TipoFuncionario tipoFuncionario;		
	
	@Column(name = "DATA_PROFILE", nullable = true)
	private Date dataProfile;
	
	@Column(name = "EMAIL", nullable = true)
	private String email;

	@Column(name = "FONE", nullable = true)
	private String fone;
	
	@Column(name = "CELULAR", nullable = true)
	private String celular;	

	@Column(name = "EMPRESA", nullable = true)
	private String empresa;
	
	@Column(name = "LOGRADOURO", nullable = true)
	private String logradouro;
	
	@Column(name = "CIDADE", nullable = true)
	private String cidade;
	
	@Column(name = "UF", nullable = true)
	private String uf;
	
	@Column(name = "CEP", nullable = true)
	private String cep;

	@Enumerated(EnumType.ORDINAL) 
	private TipoFuncionario TipoFuncionario() { 
	    return tipoFuncionario; 
	}
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinTable(name = "coordenacao_funcionario", 
	joinColumns = @JoinColumn(name = "FUNCIONARIO_ID", referencedColumnName = "UID"), 
	inverseJoinColumns = @JoinColumn(name = "COORDENACAO_ID", referencedColumnName = "UID"))
	private Coordenacao coordenacao;
	
	@ManyToOne	
	@JoinTable(name = "siapp_funcionario", 
	joinColumns = @JoinColumn(name = "FUNCIONARIO_ID", referencedColumnName = "UID") , 
	inverseJoinColumns = @JoinColumn(name = "SIAPP_ID", referencedColumnName = "UID"))
	private Siapp siapp;	
	
	@ManyToOne	
	@JoinTable(name = "ativo_funcionario", 
	joinColumns = @JoinColumn(name = "FUNCIONARIO_ID", referencedColumnName = "UID") , 
	inverseJoinColumns = @JoinColumn(name = "ATIVO_ID", referencedColumnName = "UID"))
	private Ativo ativo;

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}
	
	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {		
		if (tipoFuncionario == null ) {			
			this.tipoFuncionario = TipoFuncionario.OUTROS;
		}	
		else { 
			this.tipoFuncionario = tipoFuncionario;
		}
	}
	
	public Date getDataProfile() {
		return dataProfile;
	}

	public void setDataProfile(Date dataProfile) {
		this.dataProfile = dataProfile;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public static Funcionario fromDtoToFuncionario(FuncionarioDto funcionarioDto) {
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setUid(funcionarioDto.getUid());
		funcionario.setNome(funcionarioDto.getNome());
		funcionario.setMatricula(funcionarioDto.getMatricula());
		funcionario.setCargo(funcionarioDto.getCargo());			
		funcionario.setDepto(funcionarioDto.getDepto());
		funcionario.setTipoFuncionario(funcionarioDto.getTipoFuncionario());
		
		funcionario.setDataProfile(funcionarioDto.getDataProfile());		
		funcionario.setEmail(funcionarioDto.getEmail());		
		funcionario.setFone(funcionarioDto.getFone());
		funcionario.setCelular(funcionarioDto.getCelular());
		funcionario.setEmpresa(funcionarioDto.getEmpresa());
		funcionario.setLogradouro(funcionarioDto.getLogradouro());
		funcionario.setCidade(funcionarioDto.getCidade());
		funcionario.setUf(funcionarioDto.getUf());
		funcionario.setCep(funcionarioDto.getCep());
		
		if (funcionarioDto.getCoordenacaoDto() != null) {
		
			Coordenacao coordenacao = new Coordenacao();
			coordenacao.setUid(funcionarioDto.getCoordenacaoDto().getUid());
			funcionario.setCoordenacao(coordenacao);
		}		
		
		return funcionario;
	}

}