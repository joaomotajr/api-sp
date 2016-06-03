package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.gov.caixa.api.model.Auditoria;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
	
	@Query("select a from Auditoria a where a.coordenacao.uid = ?1")
	public List<Auditoria> findByCoordenacao(Long idCoordenacao);
	
	@Query("select a from Auditoria a where a.coordenacao.parent = ?1")
	public List<Auditoria> findByCoordenacaoParent(Long idCoordenacao);

}
