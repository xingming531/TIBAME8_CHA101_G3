package tw.idv.petradisespringboot.mall.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.mall.vo.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
}
