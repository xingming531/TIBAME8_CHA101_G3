package tw.idv.petradisespringboot.room.service;

import tw.idv.petradisespringboot.room.vo.Room;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

import java.util.List;
import java.util.Map;


public interface RoomService {
    //顯示房間資料
    List<Map<String, Object>> getRoomsByHotelId(Integer hotelId);

    //新增房間
    Room addNewRoom(Room newRoom, Integer roomTypeId);

    public Room getRoomById(Integer roomId);

    //更改房間資料
    Room updateRoom( Room updatedRoom);

    RoomType getRoomTypeById(Integer roomTypeId);
}
