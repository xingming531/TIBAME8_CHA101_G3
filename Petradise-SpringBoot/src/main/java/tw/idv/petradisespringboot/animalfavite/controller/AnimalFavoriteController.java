package tw.idv.petradisespringboot.animalfavite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tw.idv.petradisespringboot.animalfavite.repo.AnimalFavoriteRepository;
import tw.idv.petradisespringboot.animalfavite.service.AnimalFavoriteService;
import tw.idv.petradisespringboot.animalfavite.vo.AnimalFavorite;


@RestController
public class AnimalFavoriteController {

	private  AnimalFavoriteService service ;

	
	
	@GetMapping("/animalfavorites")
	@ResponseBody
	List<AnimalFavorite> getAllAnimalFavorite() {
		return service.getAllAnimalfavorite();
	}

	@PostMapping("/animalfavorites/save")
	AnimalFavorite newAnimalfavorite(@RequestBody AnimalFavorite animalfavorite) {
		return service.save(animalfavorite);
	}

	@GetMapping("/animalfavorite/id/{id}")
	AnimalFavorite one(@PathVariable Integer id) {
		return service.findAnimalfavoriteById(id);
	}

}





