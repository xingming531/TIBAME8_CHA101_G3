package tw.idv.petradisespringboot.typeFav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.hotel_owner.repo.HotelOwnerRepository;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;
import tw.idv.petradisespringboot.roomType.dto.AllHotelDTO;
import tw.idv.petradisespringboot.roomType.repo.RoomPicRepository;
import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
import tw.idv.petradisespringboot.roomType.vo.RoomPic;
import tw.idv.petradisespringboot.roomType.vo.RoomType;
import tw.idv.petradisespringboot.roomreview.repo.RoomReviewRepository;
import tw.idv.petradisespringboot.typeFav.repo.TypeFavRepository;
import tw.idv.petradisespringboot.typeFav.service.TypeFavService;
import tw.idv.petradisespringboot.typeFav.vo.TypeFav;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class TypeFavServiceImpl implements TypeFavService {
    @Autowired
    private TypeFavRepository typeFavRepository;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomPicRepository picRepository;
    @Autowired
    private RoomReviewRepository roomReviewRepository;
    @Autowired
    private HotelOwnerRepository hotelOwnerRepository;

    @Override
    public void addTypeFav(TypeFav typeFav) throws Exception {
        Optional<TypeFav> existingTypeFav = typeFavRepository.findByMemIdAndRoomTypeId(typeFav.getMemId(), typeFav.getRoomTypeId());
        if (existingTypeFav.isPresent()) {
            throw new Exception("已經收藏過這個房型了！");
        } else {
            typeFavRepository.save(typeFav);
        }
    }
    @Override
    public List<AllHotelDTO> getFavRoomTypes(Integer memberId) {
        List<TypeFav> typeFavs = typeFavRepository.findByMemId(memberId);
        List<AllHotelDTO> allHotelDTOs = new ArrayList<>();
        for (TypeFav typeFav : typeFavs) {
            RoomType roomType = roomTypeService.getRoomTypeById(typeFav.getRoomTypeId());
            HotelOwnerVO hotelOwnerVO = hotelOwnerRepository.findById(roomType.getHotelId()).get();
            AllHotelDTO allHotelDTO = new AllHotelDTO();
            // 将 roomType 的数据转换为 allHotelDTO
            if (!roomType.getRoomPics().isEmpty()) {
                RoomPic firstRoomPic = roomType.getRoomPics().get(0);  // 拿第一張圖片
                byte[] roomPicBytes = firstRoomPic.getRoomPic();
                String encodedImage = Base64.getEncoder().encodeToString(roomPicBytes);
                String imageUrl = "data:image/*;base64," + encodedImage;
                List<String> roomPics = allHotelDTO.getRoomPics();
                roomPics.add(imageUrl);
                allHotelDTO.setRoomPics(roomPics);
            }
            allHotelDTO.setRoomTypeName(roomType.getRoomTypeName());
            allHotelDTO.setRoomTypeAbout(roomType.getRoomTypeAbout());
            allHotelDTO.setRoomTypePrice(roomType.getRoomTypePrice());
            allHotelDTO.setReviewScoreTotal(hotelOwnerVO.getReviewScoreTotal());
            allHotelDTO.setHotelName(hotelOwnerVO.getHotelName());
            // 你需要根据你的实际情况来完成这个转换
            allHotelDTOs.add(allHotelDTO);
        }
        return allHotelDTOs;
    }
    @Override
    public void removeTypeFav(Integer memberId, Integer roomTypeId) {
        typeFavRepository.deleteByMemIdAndRoomTypeId(memberId, roomTypeId);
    }
}


