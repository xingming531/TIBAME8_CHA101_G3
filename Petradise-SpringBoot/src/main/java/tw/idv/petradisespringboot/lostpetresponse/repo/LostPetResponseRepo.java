package tw.idv.petradisespringboot.lostpetresponse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.lostpetresponse.controller.LostPetResponseDTO;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@Component
public interface LostPetResponseRepo extends JpaRepository<LostPetResponse, Integer>{


}
