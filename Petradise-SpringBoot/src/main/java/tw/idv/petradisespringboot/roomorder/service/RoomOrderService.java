package tw.idv.petradisespringboot.roomorder.service;

import tw.idv.petradisespringboot.roomorder.dto.ManageRoomOrderDTO;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.util.List;

public interface RoomOrderService {

    RoomOrder add(RoomOrder order);
    List<RoomOrder> getRoomOrdersByMemId(Integer memId);
    List<RoomOrder> getRoomOrdersByStatus(Character status);

    List<RoomOrder> getAll();
    RoomOrder getRoomOrderById(Integer id);
    RoomOrder modify(Integer id, RoomOrder modifiedRoomOrder);
    RoomOrder changeRoomOrderStatus(Integer id, char newStatus);

    void updateExpiredOrderStatus();

    List<RoomOrder> getRoomOrdersByHotelId(Integer hotelId);

    String getHotelNameByRoomOrderId(Integer id);

    byte[] getRoomPicByRoomOrderId(Integer id);

    Integer getHotelIdByRoomOrderId(Integer id);

    List<ManageRoomOrderDTO> getManageRoomOrderDTOs(Integer hotelId);

}
