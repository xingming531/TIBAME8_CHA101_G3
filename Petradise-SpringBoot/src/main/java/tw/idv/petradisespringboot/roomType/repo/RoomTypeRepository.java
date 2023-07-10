package tw.idv.petradisespringboot.roomType.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.roomType.dto.AllHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.searchHotelDTO;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    public List<RoomType> findByHotelId(Integer id);

    List<RoomType> findAllByHotelId(Integer hotelId);
    @Query("SELECT DISTINCT rt FROM RoomType rt JOIN HotelOwnerVO ho ON rt.hotelId = ho.hotelId JOIN RoomPic rp ON rt.roomTypeId = rp.roomTypeId WHERE ho.hotelAddress LIKE %:location% AND rt.roomPetType = :petType AND rt.roomTypeSize = :petSize AND rt.roomTypeSaleStatus = '1'")
    List<RoomType> findBySearchCriteria(@Param("location") String location, @Param("petType") String petType, @Param("petSize") Character petSize);

}


