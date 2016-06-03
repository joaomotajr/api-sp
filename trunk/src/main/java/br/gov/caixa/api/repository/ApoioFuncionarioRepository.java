package br.gov.caixa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.caixa.api.model.ApoioFuncionario;

public interface ApoioFuncionarioRepository extends JpaRepository<ApoioFuncionario, Long> {
	
	public ApoioFuncionario findByMatricula(String matricula);

}
