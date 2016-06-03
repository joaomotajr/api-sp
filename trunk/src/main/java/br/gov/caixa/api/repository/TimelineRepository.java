package br.gov.caixa.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.Timeline;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
	public List<Timeline> findByFuncionario(Funcionario funcionario);
	
	@Query("select t from Timeline t where t.funcionario.uid = ?1 and t.data > ?2")
	public List<Timeline> findByFuncionarioMaiorData(Long idfuncionario, Date dataAdmissao);	
	
	@Query("select t from Timeline t where t.funcionario.uid = ?1 and t.data < ?2 order by ?2 DESC")
	public List<Timeline> findByFuncionarioMenorData(Long idfuncionario, Date dataAdmissao);
}
