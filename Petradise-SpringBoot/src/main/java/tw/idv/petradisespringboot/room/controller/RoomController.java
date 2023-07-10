package tw.idv.petradisespringboot.room.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.room.service.RoomService;
import tw.idv.petradisespringboot.room.vo.Room;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/hotelId/{hotelId}")
    @ResponseBody
    public List<Map<String, Object>> getRoomsByHotelId(@PathVariable Integer hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @PostMapping("/addRoom")
    public ResponseEntity<Room> addRoom(@RequestBody Room newRoom, @RequestParam("roomTypeId") Integer roomTypeId) {
        Room room = roomService.addNewRoom(newRoom, roomTypeId);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    //拿到該筆房間資料(查單筆)
    @GetMapping("/{roomId}")
    @ResponseBody
    public Room getRoomById(@PathVariable Integer roomId) {
        return roomService.getRoomById(roomId);
    }

    //更新房間資料
    @PostMapping("/{roomId}/{roomTypeId}")
    public ResponseEntity<?> updateRoom(@PathVariable Integer roomId,
                                        @PathVariable Integer roomTypeId,
                                        @RequestBody Room updatedRoom) {
        RoomType roomType = roomService.getRoomTypeById(roomTypeId);

        if (roomType != null) {
            updatedRoom.setRoomType(roomType);
            updatedRoom.setRoomId(roomId);

            Room updatedRoomResult = roomService.updateRoom(updatedRoom);

            if (updatedRoomResult != null) {
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }


}

