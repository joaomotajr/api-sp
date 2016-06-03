package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.caixa.api.model.RatingFerramenta;

public interface RatingFerramentaRepository extends JpaRepository<RatingFerramenta, Long> {
	
	@Query("select a from RatingFerramenta a "
			+ "where a.funcionario.uid = ?1 "
			+ "order by a.ferramenta.categoriaTecnologica.nome, a.uid")
	public List<RatingFerramenta> findByFuncionario(Long idFuncionario);
}
