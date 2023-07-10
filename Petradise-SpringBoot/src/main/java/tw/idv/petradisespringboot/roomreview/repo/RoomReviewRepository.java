package tw.idv.petradisespringboot.roomreview.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import tw.idv.petradisespringboot.roomreview.RoomReviewDTO;
import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

import java.util.List;

@Component
public interface RoomReviewRepository extends JpaRepository<RoomReview, Integer> {

    List<RoomReview> findByHotelId(Integer hotelId);

    RoomReview findByRoomOrderId(Integer roomOrderId);

    List<RoomReview> findByHotelIdAndScore(Integer hotelId, Integer score);

    @Query("SELECT new tw.idv.petradisespringboot.roomreview.RoomReviewDTO(rr.score, rr.content, rr.roomOrder.member.name) FROM RoomReview rr WHERE rr.hotelId = :hotelId")
    List<RoomReviewDTO> findRoomReviewByHotelID(Integer hotelId);

}
