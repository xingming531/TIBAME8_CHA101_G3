package tw.idv.petradisespringboot.hotel_owner.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerAccess;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

public interface HotelOwnerRepository extends JpaRepository<HotelOwnerVO, Integer> {

	Optional<HotelOwnerVO> findByOwnerAccountAndOwnerPassword(String account, String password);

	List<HotelOwnerVO> findByOwnerAccount(String ownerAccount);

	List<HotelOwnerVO> findByhotelId(Integer hotelId);

	List<HotelOwnerVO> findByOwnerAccess(HotelOwnerAccess ownerAccess);

	List<HotelOwnerVO> findAll();
}
