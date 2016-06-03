package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.caixa.api.model.RatingAtividade;

public interface RatingAtividadeRepository extends JpaRepository<RatingAtividade, Long> {
	
	@Query("select a from RatingAtividade a "
			+ "where a.funcionario.uid = ?1 "
			+ "order by a.atividade.disciplina.nome, a.uid")
	public List<RatingAtividade> findByFuncionario(Long idFuncionario);
	
//	@Query("select a from RatingAtividade a "
//			+ "where a.funcionario.matricula like %?1")
//	public List<RatingAtividade> findByMatricula(String matricula);
	
	public List<RatingAtividade> findByFuncionarioMatriculaStartingWith(String matricula);
	
	public List<RatingAtividade> findByFuncionarioNomeContaining(String nome);
	
	public List<RatingAtividade> findByAtividadeDisciplinaNomeContainingIgnoreCase(String disciplina);

}
