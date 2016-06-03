package br.gov.caixa.api.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.Profile;

public interface ProfileRepository  extends JpaRepository<Profile, Long> {

	Profile findByUid(Long uid);
	Profile findByFuncionario(Funcionario funcionario);
	
	@Modifying
	@Transactional
	@Query("update Profile p set p.dataAdmissao = ?1, p.horaEntrada = ?2, p.horaSaida = ?3 where p.uid = ?4")
	int setInfoAdmFor(Date dataAdmissao, String horaEntrada, String horaSaida, Long uid);
}
