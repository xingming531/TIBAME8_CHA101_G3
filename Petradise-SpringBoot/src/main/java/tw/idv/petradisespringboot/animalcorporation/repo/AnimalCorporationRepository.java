package tw.idv.petradisespringboot.animalcorporation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;
import java.util.List;


import java.util.Optional;

@Component
public interface AnimalCorporationRepository extends JpaRepository<AnimalCorporation, Integer>{
	
    Optional<AnimalCorporation> findByCorpAccountAndCorpPassword(String corpAccount, String corpPassword);
	
	List<AnimalCorporation> findByAppliedStatusNot(Character appliedStatus);
	
	List<AnimalCorporation> findByAppliedStatus(Character appliedStatus);

}
