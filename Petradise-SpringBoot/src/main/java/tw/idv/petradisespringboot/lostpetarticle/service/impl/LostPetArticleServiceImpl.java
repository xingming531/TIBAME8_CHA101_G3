package tw.idv.petradisespringboot.lostpetarticle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.lostpetarticle.repo.LostPetArticleRepository;
import tw.idv.petradisespringboot.lostpetarticle.service.LostPetArticleService;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@Service
public class LostPetArticleServiceImpl implements LostPetArticleService{
	
	
	private LostPetArticleRepository lostPetArticleRepository;	

	public LostPetArticleServiceImpl(LostPetArticleRepository lostPetArticleRepository) {
		this.lostPetArticleRepository = lostPetArticleRepository;
	}

	@Override
	public List<LostPetArticle> getArticlesWithStatus() {
//	    List<LostPetArticle> articles = lostPetArticleRepository.findAll();
//	    List<LostPetArticle> filteredArticles = articles.stream()
//	            .filter(article -> "0".equals(article.getArticleStatus()))
//	            .collect(Collectors.toList());
		
	    List<LostPetArticle> articles = lostPetArticleRepository.findAll();
	    List<LostPetArticle> filteredArticles = new ArrayList<>();

	    for (LostPetArticle article : articles) {
	        if ("0".equals(article.getArticleStatus())) {
	            filteredArticles.add(article);
	        }
	    }


	    return filteredArticles;
	}
	
	@Override
	public List<LostPetArticle> getAllArticleByAdmin() {
		return lostPetArticleRepository.findAll();
	}

	@Override
	public LostPetArticle add(LostPetArticle lostPetArticle) {

		return lostPetArticleRepository.save(lostPetArticle);
	}

	@Override
	public LostPetArticle update(LostPetArticle lostPetArticle) {
	
		return lostPetArticleRepository.save(lostPetArticle);
	}

	@Override
	public void delete(Integer id) {
		lostPetArticleRepository.deleteById(id);
	}
	
	public LostPetArticle update4Status(LostPetArticle lostPetArticle) {
	    lostPetArticle.setArticleStatus("1"); 
	    return lostPetArticleRepository.save(lostPetArticle);
	}


	@Override
	public List<LostPetArticle> findByLostPlace(String lostPlace) {
		return lostPetArticleRepository.findByLostPlace(lostPlace);
	}

	@Override
	public LostPetArticle findByIdWithStatus(Integer id) {

		LostPetArticle article = lostPetArticleRepository.findById(id).orElse(null);

	    if (article != null && "1".equals(article.getArticleStatus())) {
	        return null; 
	    }

	    return article;
	}

	@Override
	public List<LostPetArticle> findBySpecies(String species) {
	    List<LostPetArticle> articles = lostPetArticleRepository.findBySpecies(species);
	    List<LostPetArticle> filteredArticles = new ArrayList<>();

	    for (LostPetArticle article : articles) {
	        if ("0".equals(article.getArticleStatus())) {
	            filteredArticles.add(article);
	        }
	    }

	    return filteredArticles;
	}

	@Override
	public List<LostPetArticle> findByMemId(Integer memId) {
	    List<LostPetArticle> articles = lostPetArticleRepository.findByMemId(memId);
	    List<LostPetArticle> filteredArticles = new ArrayList<>();

	    for (LostPetArticle article : articles) {
	        if (article.getArticleStatus().equals("0")) {
	            filteredArticles.add(article);
	        }
	    }
//	    List<LostPetArticle> articles = lostPetArticleRepository.findByMemId(memId);
//
//	    List<LostPetArticle> filteredArticles = articles.stream()
//	        .filter(article -> article.getStatus().equals("0"))
//	        .collect(Collectors.toList());


	    return filteredArticles;
	}


	@Override
	public LostPetArticle findById(Integer id) {
		return lostPetArticleRepository.findById(id).orElseThrow();
	}

}
