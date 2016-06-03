package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caixa.api.model.Coordenacao;

public interface CoordenacaoRepository extends JpaRepository<Coordenacao, Long> {
	
	public Coordenacao findByUid(Long uid);
	public Coordenacao findByNome(String nome);	
	
	public List<Coordenacao> findByParent(Coordenacao coordenacao);	
	public List<Coordenacao> findByTipo(String tipoCoordenacao);	
	public List<Coordenacao> findByCoordenadorId(Long coordenadorId);


	@Modifying
	@Transactional
	@Query("update Coordenacao c set c.parent.uid = ?1 where c.uid = ?2")
	int setParentFor(Long uid, Long parent);
	
	@Modifying
	@Transactional
	@Query("update Coordenacao c set c.coordenadorId = ?1 where c.uid = ?2")
	int setCoordenadorFor(Long coordenadorId, Long uid);
	
	@Modifying
	@Transactional
	@Query("update Coordenacao c set c.descricao = ?1 where c.uid = ?2")
	int setDescricaoFor(String descricao, Long uid);
	
	@Query("select c.descricao from Coordenacao c where c.uid = ?1")	
	List<String> obterCoordenacaoNome(Long uid);
	
	
}
