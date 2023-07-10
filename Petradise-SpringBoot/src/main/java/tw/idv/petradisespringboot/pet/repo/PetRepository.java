package tw.idv.petradisespringboot.pet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.pet.vo.Pet;
import tw.idv.petradisespringboot.pet.vo.enums.PetStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByMemberId(Integer memId);

    List<Pet> findAllByStatus(PetStatus status);

    List<Pet> findByMemberIdAndStatus(Integer memberId, PetStatus status);

    Optional<Pet> findByIdAndStatus(Integer id, PetStatus status);

    @Query(value = "SELECT COUNT(*) FROM pet_pic", nativeQuery = true)
    Integer countPetPics();

    boolean existsByName(String name);
}
