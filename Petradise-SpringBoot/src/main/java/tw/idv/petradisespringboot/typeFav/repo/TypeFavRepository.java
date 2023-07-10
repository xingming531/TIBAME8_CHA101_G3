package tw.idv.petradisespringboot.typeFav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.typeFav.vo.TypeFav;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeFavRepository extends JpaRepository<TypeFav, Integer> {
    Optional<TypeFav> findByMemIdAndRoomTypeId(Integer memberId, Integer roomTypeId);

    List<TypeFav> findByMemId(Integer memberId);
    void deleteByMemIdAndRoomTypeId(Integer memberId, Integer roomTypeId);
}