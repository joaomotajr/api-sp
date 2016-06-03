package br.gov.caixa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.caixa.api.model.BuscaMediaFuncionario;

public interface BuscaMediaFuncionarioRepository extends JpaRepository<BuscaMediaFuncionario, Long>  {
	
}
