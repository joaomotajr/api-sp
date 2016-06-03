package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.caixa.api.model.Atividade;
import br.gov.caixa.api.model.Disciplina;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
	public List<Atividade> findByDisciplina(Disciplina disciplina);
}
