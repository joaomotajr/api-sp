package br.gov.caixa.api.services;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.ProfileDto;
import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.Profile;
import br.gov.caixa.api.repository.ProfileRepository;
import br.gov.caixa.api.result.ProfileResult;

@Named
public class ProfileService {

	@Inject
	ProfileRepository repository;
	
	public ProfileResult salvar(ProfileDto dto) {
		
		Profile profile = Profile.fromDtoToProfile(dto);		
		profile = repository.save(profile);
				
		dto.setUid(profile.getUid());
		
		ProfileResult result = new ProfileResult();		
		result.setProfile(dto);		
		result.setMessage("Perfil Gravado com Sucesso.");
		
		return result;
	}
	
	public ProfileResult listOne(Long uid) {
		Profile profile = (repository.findByUid(uid));
		ProfileResult result = new ProfileResult();
		
		result.setProfile(ProfileDto.fromProfileToDto(profile));
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	
	public ProfileResult listProfilePorFuncionario(Long uid) {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setUid(uid);
		
		Profile profile = repository.findByFuncionario(funcionario);
		
		ProfileResult result = new ProfileResult();
		
		if(profile == null) {
			result.setIsError(true);
			result.setMessage("Perfil Inexistente.");
		}		
		else {
			
			
		
			result.setProfile(ProfileDto.fromProfileToDto(profile));
			result.setMessage("Executado com sucesso.");
		}
		
		return result;
	
	}

//	public int setInfoAdm(Date dataAdmissao, String horaEntrada, String horaSaida, Long uid) {
//		
//		int resp = repository.setInfoAdmFor(dataAdmissao, horaEntrada, horaSaida, uid);  
//		return resp;
//	}	

}
