package br.gov.caixa.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.gov.caixa.api.model.Coordenacao;
import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.TipoFuncionario;

public class FuncionarioDto {
	private Long uid;
	private String nome;
	private String cargo;
	private String matricula;
	private String depto;
	private TipoFuncionario tipoFuncionario;
	private CoordenacaoDto coordenacaoDto;
	private Date DataProfile;
	private String email;
	private String fone;
	private String celular;
	private String empresa;
	private String logradouro;
	private String cidade;
	private String uf;
	private String cep;

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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
		this.tipoFuncionario = tipoFuncionario;
	}
	
	public CoordenacaoDto getCoordenacaoDto() {
		return coordenacaoDto;
	}

	public void setCoordenacaoDto(CoordenacaoDto coordenacaoDto) {
		this.coordenacaoDto = coordenacaoDto;
	}
	
	public Date getDataProfile() {
		return DataProfile;
	}

	public void setDataProfile(Date dataProfile) {
		DataProfile = dataProfile;
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
	
	public static FuncionarioDto fromFuncionarioToDto(Funcionario funcionario) {
		
		FuncionarioDto dto = new FuncionarioDto();
		
		dto.setUid(funcionario.getUid());
		dto.setNome(funcionario.getNome());
		dto.setMatricula(funcionario.getMatricula());
		dto.setCargo(funcionario.getCargo());			
		dto.setDepto(funcionario.getDepto());
		dto.setTipoFuncionario(funcionario.getTipoFuncionario());
		
		dto.setDataProfile(funcionario.getDataProfile());
		dto.setEmail(funcionario.getEmail());
		dto.setFone(funcionario.getFone());
		dto.setCelular(funcionario.getCelular());
		dto.setEmpresa(funcionario.getEmpresa());
		dto.setLogradouro(funcionario.getLogradouro());
		dto.setCidade(funcionario.getCidade());
		dto.setUf(funcionario.getUf());
		dto.setCep(funcionario.getCep());		
		
		if (funcionario.getCoordenacao() != null) {
			
			Coordenacao coordenacao = new Coordenacao();
			coordenacao = funcionario.getCoordenacao(); 
			
			dto.setCoordenacaoDto(CoordenacaoDto.fromCoordenacaoToDto(coordenacao));
		}
			
		return dto;
	}

	public static List<FuncionarioDto> fromFuncionarioToListDto(List<Funcionario> list) {
		
		List<FuncionarioDto> returnList = new ArrayList<FuncionarioDto>();
		
		for (Funcionario funcionario   : list) {
			FuncionarioDto dto = new FuncionarioDto();
			
			dto.setUid(funcionario.getUid());
			dto.setNome(funcionario.getNome());
			dto.setMatricula(funcionario.getMatricula());
			dto.setCargo(funcionario.getCargo());
			dto.setDepto(funcionario.getDepto());
			dto.setTipoFuncionario(funcionario.getTipoFuncionario());
			dto.setDataProfile(funcionario.getDataProfile());
			dto.setEmail(funcionario.getEmail());
			dto.setFone(funcionario.getFone());
			dto.setCelular(funcionario.getCelular());
			dto.setEmpresa(funcionario.getEmpresa());
			dto.setLogradouro(funcionario.getLogradouro());
			dto.setCidade(funcionario.getCidade());
			dto.setUf(funcionario.getUf());
			dto.setCep(funcionario.getCep());
						
			if (funcionario.getCoordenacao() != null) {
								
				Coordenacao coordenacao = new Coordenacao();
				coordenacao = funcionario.getCoordenacao(); 
				
				dto.setCoordenacaoDto(CoordenacaoDto.fromCoordenacaoToDto(coordenacao));
			}	
			
			returnList.add(dto);
		}
		
		return returnList;
	}
}