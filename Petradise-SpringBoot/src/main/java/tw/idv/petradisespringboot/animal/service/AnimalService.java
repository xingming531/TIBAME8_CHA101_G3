package tw.idv.petradisespringboot.animal.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import tw.idv.petradisespringboot.animal.vo.Animal;

public interface AnimalService {

	Animal findAnimalById(Integer id);

	Animal save(Animal animal);

	List<Animal> findAllAnimal();
	
	Animal addAnimal(Animal animal);
	
//	List<Animal> searchAnimal(String animalType, String animalSex, String animalAge);
//	List<Animal> searchAnimal(Specification<Animal> spec);

//    用TYPE找動物	
//    List<Animal> findByAnimaltype(String animaltype);
	

    List<Animal> findAnimalByAnimalTypeAndAnimalSex(String animalType, String animalSex);

    List<Animal> findByCorpId(Integer corpId);
    
    List<Animal> findBymemId(Integer memId);

	


}
