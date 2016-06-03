package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.caixa.api.model.Ativo;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {

	public Ativo findByUid(Long uid);
	
	@Query("select a from Ativo a where a.coordenacao.uid = ?1")
	public List<Ativo> findByCoordenacao(Long idCoordenacao);
}
