package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.caixa.api.model.CategoriaTecnologica;
import br.gov.caixa.api.model.Ferramenta;

public interface FerramentaRepository extends JpaRepository<Ferramenta, Long>  {

	List<Ferramenta> findByCategoriaTecnologica(CategoriaTecnologica categoriaTecnologica);

}
