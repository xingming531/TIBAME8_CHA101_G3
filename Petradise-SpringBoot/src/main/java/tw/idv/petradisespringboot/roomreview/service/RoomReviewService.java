package tw.idv.petradisespringboot.roomreview.service;

import tw.idv.petradisespringboot.roomreview.RoomReviewDTO;
import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

import java.util.List;

public interface RoomReviewService {

    RoomReview add(RoomReview roomReview);
    RoomReview findRoomReviewById(Integer id);
    List<RoomReview> findRoomReviewByHotelId(Integer hotelId);
    RoomReview findRoomReviewByRoomOrderId(Integer roomOrderId);
    List<RoomReview> findRoomReviewByHotelIdAndScore(Integer hotelId, Integer score);

    List<RoomReviewDTO> findReviewByHotelId(Integer hotelId);
}
