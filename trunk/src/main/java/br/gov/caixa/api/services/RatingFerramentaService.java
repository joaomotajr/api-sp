package br.gov.caixa.api.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.caixa.api.dto.RatingFerramentaDto;
import br.gov.caixa.api.model.Ferramenta;
import br.gov.caixa.api.model.Funcionario;
import br.gov.caixa.api.model.RatingFerramenta;
import br.gov.caixa.api.repository.FerramentaRepository;
import br.gov.caixa.api.repository.FuncionarioRepository;
import br.gov.caixa.api.repository.RatingFerramentaRepository;
import br.gov.caixa.api.result.RatingFerramentaResult;

@Named
public class RatingFerramentaService {

	@Inject
	RatingFerramentaRepository repository;

	@Inject
	FerramentaRepository FerramentaRepository;
	
	@Inject
	FuncionarioRepository funcionarioRepository;

	public RatingFerramentaResult listByFuncionario(Long idFuncionario) {

		RatingFerramentaResult result = new RatingFerramentaResult();
		try {
			//Obtem funcionario
			Funcionario funcionario = funcionarioRepository.findOne(idFuncionario);
			
			//obtem todas Ferramentas
			List<Ferramenta> FerramentasNovas = FerramentaRepository.findAll();
			
			//Transforma Ferramentas com ratings zerado
			List<RatingFerramenta> Ferramentas = RatingFerramenta.fromFerramentaToListRatingFerramenta(FerramentasNovas, funcionario);
			
			//obtem as Ferramentas com rating
			List<RatingFerramenta> ratingFerramentas = repository.findByFuncionario(idFuncionario);
			
			//Faz merge das Ferramentas novas com as que já existem rating
			ratingFerramentas = generateListFerramentas(Ferramentas, ratingFerramentas);
			
			//Salva as Ferramentas para gerar o id
			repository.save(ratingFerramentas);
			
			//Obtém as Ferramentas com rating com seus respectivos IDS gerados
			ratingFerramentas = repository.findByFuncionario(idFuncionario);
			
			if (ratingFerramentas != null) {
				result.setList(RatingFerramentaDto.fromRatingFerramentaToListDto(ratingFerramentas));
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setMessage("Nenhum rating nas Ferramenta para esse usuário.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	private List<RatingFerramenta> generateListFerramentas(List<RatingFerramenta> Ferramentas, List<RatingFerramenta> ratingFerramentas) {
		List<RatingFerramenta> mergedRating = null;
		boolean isExiste = false;
		if(Ferramentas.size() > ratingFerramentas.size()){
			mergedRating = new ArrayList<RatingFerramenta>();
			for (RatingFerramenta Ferramenta : Ferramentas) {
				for (RatingFerramenta ratingFerramenta : ratingFerramentas) {
					if(Ferramenta.getFerramenta().getUid().equals(ratingFerramenta.getFerramenta().getUid())){
						isExiste = true;
						mergedRating.add(ratingFerramenta);
					}
				}
				
				if(!isExiste){
					mergedRating.add(Ferramenta);					
				}
				isExiste = false;
			}
		}
		return mergedRating;
	}
	
	public void save(RatingFerramentaDto dto){
		RatingFerramenta ratingFerramenta = RatingFerramenta.fromDtoToRatingFerramenta(dto);
		repository.save(ratingFerramenta);
	}
}
