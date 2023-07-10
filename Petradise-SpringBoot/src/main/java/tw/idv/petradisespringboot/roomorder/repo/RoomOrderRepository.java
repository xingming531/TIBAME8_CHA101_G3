package tw.idv.petradisespringboot.roomorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.roomorder.dto.ManageRoomOrderDTO;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomOrderRepository extends JpaRepository<RoomOrder, Integer> {

    List<RoomOrder> findByMemId(Integer memId);
    List<RoomOrder> findByStatus(Character status);

    List<RoomOrder> findByCheckOutDateGreaterThanAndStatus(LocalDate currentDate, Character status);

    @Query("SELECT ro FROM RoomOrder ro, RoomType rt WHERE ro.roomTypeId = rt.roomTypeId AND rt.hotelId = :hotelId")
    List<RoomOrder> findByHotelId(Integer hotelId);

    @Query("SELECT h.hotelName FROM RoomOrder ro JOIN RoomType rt ON ro.roomTypeId = rt.roomTypeId JOIN HotelOwnerVO h ON rt.hotelId = h.hotelId WHERE ro.id = :roomOrderId")
    String findHotelNameByRoomOrderId(Integer roomOrderId);

    @Query("SELECT rp.roomPic FROM RoomOrder ro, RoomPic rp WHERE ro.roomTypeId = rp.roomTypeId AND ro.id = :roomOrderId")
    List<byte[]> findRoomPicturesByRoomOrderId(Integer roomOrderId);

    @Query("SELECT h.hotelId FROM RoomOrder ro JOIN RoomType rt ON ro.roomTypeId = rt.roomTypeId JOIN HotelOwnerVO h ON rt.hotelId = h.hotelId WHERE ro.id = :roomOrderId")
    Integer findHotelIdByRoomOrderId(Integer roomOrderId);


    @Query("SELECT new tw.idv.petradisespringboot.roomorder.dto.ManageRoomOrderDTO(ro.id, ro.member.name, ro.petName, ro.checkInDate, ro.checkOutDate, ro.roomType.roomTypeName, ro.status) FROM RoomOrder ro WHERE ro.roomType.hotelId = :hotelId")
    List<ManageRoomOrderDTO> findManageRoomOrderDTOAll(Integer hotelId);
}
