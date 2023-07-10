package tw.idv.petradisespringboot.roomType.service;

import org.springframework.web.multipart.MultipartFile;
import tw.idv.petradisespringboot.roomType.dto.AllHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.SingleHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.searchHotelDTO;
import tw.idv.petradisespringboot.roomType.vo.RoomType;
import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

import java.time.LocalDate;
import java.util.List;

public interface RoomTypeService {
    List<RoomType> getByHotelId(Integer hotelId);


    RoomType addNewRoomType(RoomType newRoomType);


    RoomType getRoomType(Integer roomTypeId);


    void updateRoomType(Integer roomTypeId);


    RoomType updateRoomType(Integer roomTypeId, RoomType roomType, MultipartFile file1, MultipartFile file2);


    public List<AllHotelDTO> searchHotels(searchHotelDTO searchDto);

    SingleHotelDTO getSingleHotel(Integer hotelId, Integer roomTypeId,
                                  LocalDate inDay,
                                  LocalDate outDay);

    public List<String> getRoomTypeImages(Integer roomTypeId);

    public List<RoomReview> getReviewsByHotelId(Integer hotelId);

    RoomType getRoomTypeById(Integer roomTypeId);
}

