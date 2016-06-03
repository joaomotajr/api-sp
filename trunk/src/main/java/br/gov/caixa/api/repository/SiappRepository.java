package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.caixa.api.model.Siapp;

public interface SiappRepository extends JpaRepository<Siapp, Long> {

	public Siapp findByUid(Long uid);
	public Siapp findByCodigo(Long Codigo);
	public List<Siapp> findByCoordenacaoProjeto(String nomeCoordenacao);	
}
