package br.gov.caixa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.caixa.api.model.Coordenacao;
import br.gov.caixa.api.model.Funcionario;;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	public Funcionario findByMatricula(String matricula);
	
	public Funcionario findByUid(Long uid);

	public List<Funcionario> findByCoordenacao(Coordenacao coordenacao);
	
	public List<Funcionario> findByNomeContainingIgnoreCase(String nome);
	

}
