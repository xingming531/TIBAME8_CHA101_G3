package tw.idv.petradisespringboot.roomreview.controller;

import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.roomreview.RoomReviewDTO;
import tw.idv.petradisespringboot.roomreview.service.RoomReviewService;
import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

import java.util.List;

@RestController
public class RoomReviewController {

    private final RoomReviewService service;

    public RoomReviewController(RoomReviewService service) {
        this.service = service;
    }
    @PostMapping("/roomreview/add-review")
    RoomReview addReview(@RequestBody RoomReview review){
        return service.add(review);
    }
    @GetMapping("/roomreview/id/{id}")
    RoomReview findById(@PathVariable Integer id){
    return service.findRoomReviewById(id);
    }
    @GetMapping("/roomreview/hotel-id/{hotelId}")
    List<RoomReview> findByHotelId(@PathVariable Integer hotelId){
        return service.findRoomReviewByHotelId(hotelId);
    }
    @GetMapping("/roomreview/room-order-id/{roomOrderId}")
    RoomReview findByRoomOrderId(@PathVariable Integer roomOrderId){
        return service.findRoomReviewByRoomOrderId(roomOrderId);
    }
    @GetMapping("/roomreview/hotel-id/{hotelId}/score/{score}")
    List<RoomReview> findByHotelIdAndScore(@PathVariable Integer hotelId, @PathVariable Integer score){
        return service.findRoomReviewByHotelIdAndScore(hotelId, score);
    }


    @GetMapping("/roomreview/hotel-id/{hotelId}/dto")
    List<RoomReviewDTO> findReviewByHotelId(@PathVariable Integer hotelId){
        return service.findReviewByHotelId(hotelId);
    }

}
