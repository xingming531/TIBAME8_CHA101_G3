package tw.idv.petradisespringboot.roomorder.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.roomorder.dto.ManageRoomOrderDTO;
import tw.idv.petradisespringboot.roomorder.repo.RoomOrderRepository;
import tw.idv.petradisespringboot.roomorder.service.RoomOrderService;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.time.LocalDate;
import java.util.List;
@Service
public class RoomOrderServiceImpl implements RoomOrderService {

    private final RoomOrderRepository repository;

    public RoomOrderServiceImpl(RoomOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomOrder add(RoomOrder order) {
        return repository.save(order);
    }

    @Override
    public List<RoomOrder> getRoomOrdersByMemId(Integer memId) {
        return repository.findByMemId(memId);
    }

    @Override
    public List<RoomOrder> getRoomOrdersByStatus(Character status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<RoomOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public RoomOrder getRoomOrderById(Integer id) {
        return repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid Order ID"));
    }

    @Override
    public RoomOrder modify(Integer id, RoomOrder modifiedRoomOrder) {
        // Update the fields of the existing room order with the new values
        return repository.findById(id).map(existingRoomOrder ->{
//            existingRoomOrder.setRoomTypeId(modifiedRoomOrder.getRoomTypeId());
            existingRoomOrder.setRoomId(modifiedRoomOrder.getRoomId());
            existingRoomOrder.setPetName(modifiedRoomOrder.getPetName());
            existingRoomOrder.setOrderDate(modifiedRoomOrder.getOrderDate());
            existingRoomOrder.setCheckInDate(modifiedRoomOrder.getCheckInDate());
            existingRoomOrder.setCheckOutDate(modifiedRoomOrder.getCheckOutDate());
            existingRoomOrder.setStatus(modifiedRoomOrder.getStatus());
            existingRoomOrder.setPayMethod(modifiedRoomOrder.getPayMethod());
            existingRoomOrder.setPrice(modifiedRoomOrder.getPrice());
            existingRoomOrder.setSpecialReq(modifiedRoomOrder.getSpecialReq());
            return repository.save(existingRoomOrder);
        }).
        orElseThrow(() -> new IllegalArgumentException("Invalid Order ID"));
    }

    @Override
    public RoomOrder changeRoomOrderStatus(Integer id, char newStatus) {
        return repository.findById(id).map(roomOrder -> {
            roomOrder.setStatus(newStatus);
            return repository.save(roomOrder);
        }).
                orElseThrow(() -> new IllegalArgumentException("Invalid Order ID"));
    }

    @Override
    public void updateExpiredOrderStatus() {
        // Implement updateExpiredOrderStatus logic
        LocalDate currentDate = LocalDate.now();
        List<RoomOrder> upcomingOrders = repository.findByCheckOutDateGreaterThanAndStatus(currentDate, '0');
        upcomingOrders.forEach(order -> {
            order.setStatus('1');
            repository.save(order);
        });
    }

    @Override
    public List<RoomOrder> getRoomOrdersByHotelId(Integer hotelId) {
        return repository.findByHotelId(hotelId);
    }

    @Override
    public String getHotelNameByRoomOrderId(Integer id) {
        return repository.findHotelNameByRoomOrderId(id);
    }

    @Override
    public byte[] getRoomPicByRoomOrderId(Integer roomOrderId) {
        List<byte[]> roomPictures = repository.findRoomPicturesByRoomOrderId(roomOrderId);
        if (!roomPictures.isEmpty()) {
            return roomPictures.get(0);
        }
        return null;
    }

    @Override
    public Integer getHotelIdByRoomOrderId(Integer id) {
        return repository.findHotelIdByRoomOrderId(id);
    }


    @Override
    public List<ManageRoomOrderDTO> getManageRoomOrderDTOs(Integer hotelId) {
        return repository.findManageRoomOrderDTOAll(hotelId);
    }
}
