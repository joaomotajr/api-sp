package br.gov.caixa.api.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.RatingAtividadeDto;
import br.gov.caixa.api.model.Atividade;
import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.RatingAtividade;
import br.gov.caixa.api.repository.AtividadeRepository;
import br.gov.caixa.api.repository.FuncionarioRepository;
import br.gov.caixa.api.repository.RatingAtividadeRepository;
import br.gov.caixa.api.result.RatingAtividadeResult;

@Named
public class RatingAtividadeService {

	@Inject
	RatingAtividadeRepository repository;

	@Inject
	AtividadeRepository atividadeRepository;
	
	@Inject
	FuncionarioRepository funcionarioRepository;

	public RatingAtividadeResult listByFuncionario(Long idFuncionario) {

		RatingAtividadeResult result = new RatingAtividadeResult();
		try {
			//Obtem funcionario
			Funcionario funcionario = funcionarioRepository.findOne(idFuncionario);
			
			//obtem todas atividades
			List<Atividade> atividadesNovas = atividadeRepository.findAll();
			
			//Transforma atividades com ratings zerado
			List<RatingAtividade> atividades = RatingAtividade.fromAtividadetoListRatingAtividade(atividadesNovas, funcionario);
			
			//obtem as atividades com rating
			List<RatingAtividade> ratingAtividades = repository.findByFuncionario(idFuncionario);
			
			//Faz merge das atividades novas com as que já existem rating
			ratingAtividades = generateListAtividades(atividades, ratingAtividades);
			
			//Salva as atividades para gerar o id
			repository.save(ratingAtividades);
			
			//Obtém as atividades com rating com seus respectivos IDS gerados
			ratingAtividades = repository.findByFuncionario(idFuncionario);
			
			if (ratingAtividades != null) {
				result.setList(RatingAtividadeDto.fromRatingAtividadeToListDto(ratingAtividades));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhum rating nas atividade para esse usuário.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	private List<RatingAtividade> generateListAtividades(List<RatingAtividade> atividades, List<RatingAtividade> ratingAtividades) {
		List<RatingAtividade> mergedRating = null;
		boolean isExiste = false;
		if(atividades.size() > ratingAtividades.size()){
			mergedRating = new ArrayList<RatingAtividade>();
			for (RatingAtividade atividade : atividades) {
				for (RatingAtividade ratingAtividade : ratingAtividades) {
					if(atividade.getAtividade().getUid().equals(ratingAtividade.getAtividade().getUid())){
						isExiste = true;
						mergedRating.add(ratingAtividade);
					}
				}
				
				if(!isExiste){
					mergedRating.add(atividade);					
				}
				isExiste = false;
			}
		}
		return mergedRating;
	}
	
	public void save(RatingAtividadeDto dto){
		RatingAtividade ratingAtividade = RatingAtividade.fromDtoToRatingAtividade(dto);
		repository.save(ratingAtividade);
	}
	
	public RatingAtividadeResult obterRatingPorMatricula(String matricula) {
		RatingAtividadeResult result = new RatingAtividadeResult();
		try {
			List<RatingAtividade> ratingAtividades = repository.findByFuncionarioMatriculaStartingWith(matricula);

			if (ratingAtividades != null) {
				result.setList(RatingAtividadeDto.fromRatingAtividadeToListDto(ratingAtividades));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhum rating nas atividade para esse usuário.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public RatingAtividadeResult obterRatingPorNome(String nome) {
		RatingAtividadeResult result = new RatingAtividadeResult();
		try {
			List<RatingAtividade> ratingAtividades = repository.findByFuncionarioNomeContaining(nome);
			if (ratingAtividades != null) {
				result.setList(RatingAtividadeDto.fromRatingAtividadeToListDto(ratingAtividades));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhum rating nas atividade para esse usuário.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public RatingAtividadeResult obterRatingPorDisciplina(String disciplina) {
		RatingAtividadeResult result = new RatingAtividadeResult();
		try {
			List<RatingAtividade> ratingAtividades = repository.findByAtividadeDisciplinaNomeContainingIgnoreCase(disciplina);
			if (ratingAtividades != null) {
				result.setList(RatingAtividadeDto.fromRatingAtividadeToListDto(ratingAtividades));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhum rating nas atividade para esse usuário.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
