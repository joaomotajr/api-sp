package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.dto.ProfileDto;
import br.gov.caixa.api.dto.RatingAtividadeDto;
import br.gov.caixa.api.dto.RatingFerramentaDto;
import br.gov.caixa.api.result.ProfileResult;
import br.gov.caixa.api.result.RatingAtividadeResult;
import br.gov.caixa.api.result.RatingFerramentaResult;
import br.gov.caixa.api.services.ProfileService;
import br.gov.caixa.api.services.RatingAtividadeService;
import br.gov.caixa.api.services.RatingFerramentaService;

@RestController
public class ProfileController {
	
	@Inject
	ProfileService service;
	
	@Inject
	RatingAtividadeService serviceRating;
	
	@Inject
	RatingFerramentaService serviceRatingFerramenta;
	
	
	@RequestMapping(value="/api/profile/salvarProfile", 
			method=RequestMethod.POST,
			consumes = "application/json",  
			produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ProfileResult salvarProfile(@RequestBody ProfileDto dto) {
		
		return service.salvar(dto);
	}
	
	@RequestMapping(value="/api/profile/listProfilePorIdFuncionario/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ProfileResult listOne(@PathVariable Long uid) {
		
		return service.listProfilePorFuncionario(uid);
	}
	
	@RequestMapping(value="/api/profile/listprofilePorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ProfileResult listProfilePorId(@PathVariable Long uid) {
		
		return service.listOne(uid);
	}
	
	@RequestMapping(value = "/api/profile/obterRatingPorIdFuncionario/{idFuncionario}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public RatingAtividadeResult listByFuncionario(@PathVariable Long idFuncionario) {
		return serviceRating.listByFuncionario(idFuncionario);
	}
	
	@RequestMapping(value = "/api/profile/obterRatingFerramentaPorIdFuncionario/{idFuncionario}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public RatingFerramentaResult listFerramentaByFuncionario(@PathVariable Long idFuncionario) {
		return serviceRatingFerramenta.listByFuncionario(idFuncionario);
	}
	
	@RequestMapping(value = "/api/profile/salvarRating", method=RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void saveRating(@RequestBody RatingAtividadeDto dto) {
		serviceRating.save(dto);
	}
	
	@RequestMapping(value = "/api/profile/salvarRatingFerramenta", method=RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void saveRatingFerramenta(@RequestBody RatingFerramentaDto dto) {
		serviceRatingFerramenta.save(dto);
	}
}



