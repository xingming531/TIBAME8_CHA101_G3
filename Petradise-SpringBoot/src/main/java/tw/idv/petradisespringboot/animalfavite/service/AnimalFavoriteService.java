package tw.idv.petradisespringboot.animalfavite.service;

import java.util.List;

import tw.idv.petradisespringboot.animalfavite.vo.AnimalFavorite;

public interface AnimalFavoriteService {

	List<AnimalFavorite> getAllAnimalfavorite();

	AnimalFavorite findAnimalfavoriteById(Integer id);
	
	AnimalFavorite save (AnimalFavorite animalfavorite);



}
