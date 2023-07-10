package tw.idv.petradisespringboot.animalapplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.animalapplication.vo.AdoptedApplication;




@Component
public interface AdoptedApplicationRepository extends JpaRepository<AdoptedApplication, Integer>{
	
    



 
}
	

