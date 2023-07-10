package tw.idv.petradisespringboot.roomType.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.idv.petradisespringboot.roomType.dto.AllHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.SingleHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.searchHotelDTO;
import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
import tw.idv.petradisespringboot.roomType.vo.RoomType;
import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

@Controller
@RequestMapping("/roomTypes")
public class RoomTypeController {
    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService service) {
        super();
        this.service = service;
    }

    //拿到該業主的所有房型資料
    @GetMapping("/hotelId/{hotelId}")
    @ResponseBody
    List<RoomType> getByHotelId(@PathVariable Integer hotelId) {
        return service.getByHotelId(hotelId);
    }

    //新增房型
    @PostMapping("/addRoomType")
    @ResponseBody
    public void addRoomType(@RequestBody RoomType newRoomType) {
        service.addNewRoomType(newRoomType);
    }

    //拿到該筆房型資料(查單筆)
    @GetMapping("/{roomTypeId}")
    @ResponseBody
    public RoomType getRoomTypeWithPics(@PathVariable Integer roomTypeId) {
        return service.getRoomType(roomTypeId);
    }

    //修改房型資料
    @PostMapping(path = "/{roomTypeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public RoomType updateRoomType(@PathVariable Integer roomTypeId,
                                   @RequestParam(value = "room_type_name") String roomTypeName,
                                   @RequestParam(value = "room_type_sale_status") Character roomTypeSaleStatus,
                                   @RequestParam(value = "room_type_price") Integer roomTypePrice,
                                   @RequestParam(value = "room_pet_type") String roomPetType,
                                   @RequestParam(value = "room_type_size") Character roomTypeSize,
                                   @RequestParam(value = "room_type_about") String roomTypeAbout,
                                   @RequestParam(value = "room_pic1") MultipartFile file1,
                                   @RequestParam(value = "room_pic2") MultipartFile file2) {
        RoomType roomType = new RoomType();
        roomType.setRoomTypeName(roomTypeName);
        roomType.setRoomTypeSaleStatus(roomTypeSaleStatus);
        roomType.setRoomTypePrice(roomTypePrice);
        roomType.setRoomPetType(roomPetType);
        roomType.setRoomTypeSize(roomTypeSize);
        roomType.setRoomTypeAbout(roomTypeAbout);
        return service.updateRoomType(roomTypeId, roomType, file1, file2);
    }

    //更新房間數量
    @PostMapping("/updateRooms/{roomTypeId}")
    public ResponseEntity<?> updateRoomType(@PathVariable Integer roomTypeId) {
        service.updateRoomType(roomTypeId);
        return ResponseEntity.ok().build();
    }
    //使用者送篩想要的房型
    @PostMapping("/search")
    public ResponseEntity<List<AllHotelDTO>> searchHotels(@RequestBody searchHotelDTO searchDto) {
        List<AllHotelDTO> searchResults = service.searchHotels(searchDto);
        return ResponseEntity.ok(searchResults);
    }

    //使用者點選符合自己篩選條件的房型
    @PostMapping ("/choose/{hotelId}/{roomTypeId}/{inDay}/{outDay}")
    @ResponseBody
    public SingleHotelDTO getSingleHotel(@PathVariable Integer hotelId, @PathVariable Integer roomTypeId, @PathVariable
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inDay, @PathVariable
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate outDay) {
        return service.getSingleHotel(hotelId, roomTypeId, inDay, outDay);
    }


    //使用者選擇單一房型後顯示圖片
    @GetMapping("/{roomTypeId}/images")
    @ResponseBody
    public List<String> getRoomTypeImages(@PathVariable Integer roomTypeId) {
        return service.getRoomTypeImages(roomTypeId);
    }

    //顯示該旅館評論
    @GetMapping("/hotels/{hotelId}/reviews")
    public ResponseEntity<List<RoomReview>> getReviewsByHotelId(@PathVariable Integer hotelId) {
        List<RoomReview> reviews = service.getReviewsByHotelId(hotelId);
        return ResponseEntity.ok(reviews);
    }

}
