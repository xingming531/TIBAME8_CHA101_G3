package tw.idv.petradisespringboot.animal.serviceimpl;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.animal.contrllor.AnimalSpecifications;
import tw.idv.petradisespringboot.animal.repo.AnimalRepository;
import tw.idv.petradisespringboot.animal.service.AnimalService;
import tw.idv.petradisespringboot.animal.vo.Animal;


@Service
public class AnimalServiceImpl implements AnimalService {

	private final AnimalRepository repository;

	AnimalServiceImpl(AnimalRepository repository) {
		this.repository = repository;

	}

	@Override
	public List<Animal> findAllAnimal() {

		return repository.findAll();
	}

	@Override
	public Animal findAnimalById(Integer id) {

		return repository.findById(id).orElseThrow(() -> new AnimalNotFoundException(id));
	}

	@Override
	public Animal save(Animal animal) {
		return repository.save(animal);
	}
	

	@Override
	public Animal addAnimal(Animal animal) {

		return repository.save(animal);
	}
	


//  用TYPE找動物	
	@Override
	public List<Animal> findAnimalByAnimalTypeAndAnimalSex(String animalType, String animalSex) {
		
		return repository.findAnimalByAnimalTypeAndAnimalSex(animalType, animalSex);
	}

	@Override
	public List<Animal> findByCorpId(Integer corpId) {
		return repository.findByCorpId(corpId);
	}

	@Override
	public List<Animal> findBymemId(Integer memId) {
		return repository.findBymemId(memId);
	}

}
class AnimalNotFoundException extends RuntimeException {
	AnimalNotFoundException(Integer id) {
		super("找不到會員ID: " + id);
	}
}
