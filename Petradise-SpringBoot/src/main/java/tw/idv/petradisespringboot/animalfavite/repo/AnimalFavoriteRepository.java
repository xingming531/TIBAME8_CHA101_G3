package tw.idv.petradisespringboot.animalfavite.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.idv.petradisespringboot.animalfavite.vo.AnimalFavorite;

public interface AnimalFavoriteRepository extends JpaRepository<AnimalFavorite,Integer>{

}
