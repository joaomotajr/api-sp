package br.gov.caixa.api.result;

import java.util.List;

import br.gov.caixa.api.dto.ProfileDto;

public class ProfileResult extends BasicResult  {
	private List<ProfileDto> list;
	private ProfileDto profile;	

	public List<ProfileDto> getList() {
		return list;
	}

	public void setList(List<ProfileDto> list) {
		this.list = list;
	}

	public ProfileDto getProfile() {
		return profile;
	}

	public void setProfile(ProfileDto profile) {	
		this.profile = profile;
	}
}
