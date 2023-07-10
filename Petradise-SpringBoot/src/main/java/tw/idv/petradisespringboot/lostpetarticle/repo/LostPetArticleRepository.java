package tw.idv.petradisespringboot.lostpetarticle.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@Component
public interface LostPetArticleRepository extends JpaRepository<LostPetArticle, Integer>{

	List<LostPetArticle> findBySpecies(String species);

	List<LostPetArticle> findByLostPlace(String lostPlace);
	
	List<LostPetArticle> findByMemId(Integer memId);
	
}
