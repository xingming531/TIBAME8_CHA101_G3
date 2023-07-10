package tw.idv.petradisespringboot.roomorder.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.roomorder.dto.ManageRoomOrderDTO;
import tw.idv.petradisespringboot.roomorder.service.RoomOrderService;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room-order")
public class RoomOrderController {

    private final RoomOrderService service;
    public RoomOrderController(RoomOrderService service) {
        this.service = service;
    }

    @PostMapping("/customer/add")
    public RoomOrder add(@RequestBody RoomOrder order){
        return service.add(order);
    }
    @GetMapping("/customer/mem-id/{memId}")
    public List<RoomOrder> getRoomOrdersByMemId(@PathVariable Integer memId) {
        return service.getRoomOrdersByMemId(memId);
    }
    @GetMapping("/customer/status/{status}")
    public List<RoomOrder> getRoomOrdersByStatus(@PathVariable Character status) {
        return service.getRoomOrdersByStatus(status);
    }

    @GetMapping("/all")
    public List<RoomOrder> getAllOrder(){
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public RoomOrder getRoomOrderById(@PathVariable Integer id){
        return service.getRoomOrderById(id);
    }

    @PutMapping("id/{id}/modify")
    public RoomOrder modify(@PathVariable Integer id, @RequestBody RoomOrder modifiedRoomOrder){
        return service.modify(id, modifiedRoomOrder);
    }

    @PutMapping("id/{id}/change-status")
    public RoomOrder changeRoomOrderStatus(@PathVariable Integer id, @RequestBody Map<String, String> requestBody){
        String newStatus = requestBody.get("status");
        char status = newStatus.charAt(0);
        return service.changeRoomOrderStatus(id, status);
    }

    @GetMapping("/hotel-id/{hotelId}")
    public ResponseEntity<List<RoomOrder>> getRoomOrdersByHotelId(@PathVariable Integer hotelId) {
        List<RoomOrder> roomOrders = service.getRoomOrdersByHotelId(hotelId);
        return ResponseEntity.ok(roomOrders);
    }

    @GetMapping("/hotel-name/{id}")
    public ResponseEntity<String> getHotelNameByRoomOrderId(@PathVariable Integer id){
        String hotelName = service.getHotelNameByRoomOrderId(id);
        if (hotelName != null) {
            return ResponseEntity.ok(hotelName);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/room-picture")
    public ResponseEntity<byte[]> getRoomPictureByRoomOrderId(@PathVariable Integer id) {
        byte[] roomPicture = service.getRoomPicByRoomOrderId(id);
        if (roomPicture != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(roomPicture, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/hotel-id/{id}")
    public Integer getHotelIdByRoomOrderId(@PathVariable Integer id) {
        return service.getHotelIdByRoomOrderId(id);
    }


    @GetMapping("/manage-room-order/{hotelId}")
    public List<ManageRoomOrderDTO> getMangeRoomOrder(@PathVariable Integer hotelId) {
        return service.getManageRoomOrderDTOs(hotelId);
    }
}
