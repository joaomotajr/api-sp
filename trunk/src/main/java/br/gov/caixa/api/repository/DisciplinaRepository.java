package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.caixa.api.model.Disciplina;
import br.gov.caixa.api.model.Metodologia;	

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

	public List<Disciplina> findByMetodologia(Metodologia metodologia);
}
