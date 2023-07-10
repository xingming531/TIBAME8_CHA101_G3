package tw.idv.petradisespringboot.animalfavite.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.animal.repo.AnimalRepository;
import tw.idv.petradisespringboot.animalfavite.repo.AnimalFavoriteRepository;
import tw.idv.petradisespringboot.animalfavite.service.AnimalFavoriteService;
import tw.idv.petradisespringboot.animalfavite.vo.AnimalFavorite;

@Service

public class AnimalFavoriteServiceImpl implements AnimalFavoriteService {

	private final AnimalFavoriteRepository repository;

	AnimalFavoriteServiceImpl(AnimalFavoriteRepository repository) {
		this.repository = repository;

	}

	@Override
	public List<AnimalFavorite> getAllAnimalfavorite() {

		return repository.findAll();
	}

	@Override
	public AnimalFavorite findAnimalfavoriteById(Integer id) {

		return repository.findById(id).orElseThrow(() -> new Animal_favoriteNotFoundException(id));
	}

	@Override
	public AnimalFavorite save(AnimalFavorite animalFavorite) {
		return repository.save(animalFavorite);

	}

}

class Animal_favoriteNotFoundException extends RuntimeException {
	Animal_favoriteNotFoundException(Integer id) {
		super("找不到會員ID: " + id);
	}
}
