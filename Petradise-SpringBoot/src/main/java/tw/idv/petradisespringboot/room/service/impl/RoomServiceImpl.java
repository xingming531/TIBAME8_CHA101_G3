package tw.idv.petradisespringboot.room.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.room.repo.RoomRepository;
import tw.idv.petradisespringboot.room.service.RoomService;
import tw.idv.petradisespringboot.room.vo.Room;
import tw.idv.petradisespringboot.roomType.repo.RoomTypeRepository;
import tw.idv.petradisespringboot.roomType.service.impl.RoomTypeServiceImpl.ResourceNotFoundException;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository typeRepository;

    public RoomServiceImpl(RoomRepository roomRepository, RoomTypeRepository typeRepository) {
        this.roomRepository = roomRepository;
        this.typeRepository = typeRepository;
    }

    public List<Map<String, Object>> getRoomsByHotelId(Integer hotelId) {
        List<Room> rooms = roomRepository.findRoomsByHotelId(hotelId);
        List<Map<String, Object>> roomMaps = new ArrayList<>();
        for (Room room : rooms) {
            Map<String, Object> roomMap = new HashMap<>();
            roomMap.put("roomId", room.getRoomId());
            roomMap.put("roomTypeName", room.getRoomType().getRoomTypeName());
            roomMap.put("petName", room.getPetName());
            roomMap.put("roomName", room.getRoomName());
            roomMap.put("roomSaleStatus", room.getRoomSaleStatus());
            roomMap.put("roomStatus", room.getRoomStatus());
            roomMaps.add(roomMap);
        }
        return roomMaps;
    }

    //新增房間
    @Override
    public Room addNewRoom(Room newRoom, Integer roomTypeId) {
    //roomTypeId 設置到 newRoom 物件的 roomType 屬性中
        RoomType roomType = typeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id " + roomTypeId));
        newRoom.setRoomType(roomType);
        return roomRepository.save(newRoom);
    }

    //拿到單筆房間
    @Override
    public Room getRoomById(Integer roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    @Override
    public Room updateRoom(Room updatedRoom) {
        Room existingRoom = roomRepository.findById(updatedRoom.getRoomId()).orElse(null);
        if (existingRoom != null) {
            // 更新房間屬性
            existingRoom.setRoomName(updatedRoom.getRoomName());
            existingRoom.setPetName(updatedRoom.getPetName());
            existingRoom.setRoomSaleStatus(updatedRoom.getRoomSaleStatus());
            existingRoom.setRoomStatus(updatedRoom.getRoomStatus());
            existingRoom.setRoomType(updatedRoom.getRoomType());
            // 保存更新後的房間
            return roomRepository.save(existingRoom);
        } else {
            return null;
        }
    }

    @Override
    public RoomType getRoomTypeById(Integer roomTypeId) {
        return typeRepository.findById(roomTypeId).orElse(null);
    }
}
