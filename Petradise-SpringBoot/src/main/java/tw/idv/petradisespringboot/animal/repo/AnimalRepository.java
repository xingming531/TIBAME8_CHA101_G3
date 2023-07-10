package tw.idv.petradisespringboot.animal.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.animal.vo.Animal;



@Component
public interface AnimalRepository extends JpaRepository<Animal, Integer> {


//    @Query("SELECT a FROM Animal a WHERE animaltype = :animaltype and animalSex=:animalSex and animalage =:animalage") // 编写自定义查询逻辑
//    List<Animal> searchAnimals(Specification<Animal> specification);
	
	
//	    @Query("FROM Animal  WHERE animaltype = :animaltype and animalSex = :animalSex and animalage = :animalage") // 编写自定义查询逻辑
//	    List<Animal> searchAnimals(@Param("animaltype")String animaltype,@Param("animalSex")String animalSex,@Param("animalage")String animalage);

//	    @Query("FROM Animal a WHERE animaltype = :animaltype") // 编写自定义查询逻辑
//	    List<Animal> searchAnimals(@Param("animaltype")String animaltype);
	    
//	    用TYPE找動物
//	    List<Animal> findByAnimaltype(String animaltype);


	List<Animal> findAnimalByAnimalTypeAndAnimalSex(String animalType, String animalSex);

	List<Animal> findByCorpId(Integer corpId);
	
	List<Animal> findBymemId(Integer memId);

	
	
}
