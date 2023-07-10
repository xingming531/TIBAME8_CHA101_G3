package tw.idv.petradisespringboot.room.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.room.vo.Room;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    //r=room實體物件
    @Query("SELECT r FROM Room r JOIN FETCH r.roomType WHERE r.roomType.hotelId = :hotelId")
    List<Room> findRoomsByHotelId(@Param("hotelId") Integer hotelId);
}
