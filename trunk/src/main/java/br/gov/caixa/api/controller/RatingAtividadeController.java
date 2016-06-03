package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.result.RatingAtividadeResult;
import br.gov.caixa.api.services.RatingAtividadeService;

@RestController
public class RatingAtividadeController {
	
	@Inject
	RatingAtividadeService service;
	
	@RequestMapping(value = "/api/ratingAtividades/obterRatingPorIdFuncionario/{idFuncionario}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public RatingAtividadeResult listByFuncionario(@PathVariable Long idFuncionario) {
		return service.listByFuncionario(idFuncionario);
	}
	
	@RequestMapping(value = "/api/ratingAtividades/obterRatingPorMatricula/{matricula}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public RatingAtividadeResult obterRatingPorMatricula(@PathVariable String matricula) {
		return service.obterRatingPorMatricula(matricula);
	}
	
	@RequestMapping(value = "/api/ratingAtividades/obterRatingPorNome/{nome}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public RatingAtividadeResult obterRatingPorNome(@PathVariable String nome) {
		return service.obterRatingPorNome(nome);
	}
	
	@RequestMapping(value = "/api/ratingAtividades/obterRatingPorDisciplina/{disciplina}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public RatingAtividadeResult obterRatingPorDisciplina(@PathVariable String disciplina) {
		return service.obterRatingPorDisciplina(disciplina);
	}

}
