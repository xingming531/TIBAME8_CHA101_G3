package tw.idv.petradisespringboot.roomType.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tw.idv.petradisespringboot.hotel_owner.repo.HotelOwnerRepository;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;
import tw.idv.petradisespringboot.room.vo.Room;
import tw.idv.petradisespringboot.roomType.dto.AllHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.SingleHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.searchHotelDTO;
import tw.idv.petradisespringboot.roomType.repo.RoomPicRepository;
import tw.idv.petradisespringboot.roomType.repo.RoomTypeRepository;
import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
import tw.idv.petradisespringboot.roomType.vo.RoomPic;
import tw.idv.petradisespringboot.roomType.vo.RoomType;
import tw.idv.petradisespringboot.roomreview.repo.RoomReviewRepository;
import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeRepository typeRepository;
    @Autowired
    private RoomPicRepository picRepository;
    @Autowired
    private RoomReviewRepository roomReviewRepository;
    @Autowired
    private HotelOwnerRepository hotelOwnerRepository;


    //取得該業主的所有房型
    @Override
    public List<RoomType> getByHotelId(Integer hotelId) {

        return typeRepository.findByHotelId(hotelId);

    }

    //新增房型
    @Override
    public RoomType addNewRoomType(RoomType newRoomType) {
        return typeRepository.save(newRoomType);
    }

    //取得特定房型
    @Override
    public RoomType getRoomType(Integer roomTypeId) {
        return typeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id " + roomTypeId));

    }

    //新增房間時更新房型數量
    @Override
    public void updateRoomType(Integer roomTypeId) {
        RoomType roomType = typeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id " + roomTypeId));

        roomType.setRoomTypeAmount(roomType.getRoomTypeAmount() + 1);  // 增加房間數量

        typeRepository.save(roomType);
    }


    //更新房型
    @Override
    public RoomType updateRoomType(Integer roomTypeId, RoomType roomType, MultipartFile file1, MultipartFile file2) {
        // 拿到原有的房型資訊
        RoomType existingRoomType = typeRepository.findById(roomTypeId)//找到指定的roomtypeid
                .orElseThrow(() -> new RoomTypeNotFoundException(roomTypeId));

        // 透過roomtypeid找到對應的圖片
        List<RoomPic> existingPics = picRepository.findByRoomType_RoomTypeId(roomTypeId);

        // 更新房型資訊
        // 把roomtype物件裡面的值設定給existingRoomType
        Character roomTypeSaleStatus = roomType.getRoomTypeSaleStatus();
        if (roomTypeSaleStatus != null) {
            existingRoomType.setRoomTypeSaleStatus(roomTypeSaleStatus);
            if (roomTypeSaleStatus == '0') {
                for (Room room : existingRoomType.getRooms()) {
                    room.setRoomSaleStatus('0');
                }
            } else if (roomTypeSaleStatus == '1') {
                for (Room room : existingRoomType.getRooms()) {
                    room.setRoomSaleStatus('1');
                }
            }
        }
        existingRoomType.setRoomTypeName(roomType.getRoomTypeName());
        existingRoomType.setRoomTypePrice(roomType.getRoomTypePrice());
        existingRoomType.setRoomPetType(roomType.getRoomPetType());
        existingRoomType.setRoomTypeSize(roomType.getRoomTypeSize());
        existingRoomType.setRoomTypeAbout(roomType.getRoomTypeAbout());

        MultipartFile[] files = {file1, file2}; //把圖片們裝進陣列 處理待處理的圖片
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                RoomPic roomPic;
                if (existingPics.size() > i) {
                    // existingPics.size() 表格內已存在的圖片數量
//                  System.out.println("existingPics.size() = " + existingPics.size());
                    roomPic = existingPics.get(i); //把圖片表格裡的第i+1張照片拿出來
                } else {
                    roomPic = new RoomPic();// 新增圖片物件
                    roomPic.setRoomType(existingRoomType);//關聯roomtype屬性跟圖片
                    existingPics.add(roomPic);
                }
                try {
                    byte[] picData = file.getBytes(); //透過getBytes() 取得圖片陣列
                    roomPic.setRoomPic(picData);//更新圖片表格的圖片
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        existingRoomType.setRoomPics(existingPics);//更新房型vo裡的圖片
//        RoomType updatedRoomType = typeRepository.save(existingRoomType);
        existingRoomType.getRoomPics().forEach(pic -> {
            pic.setRoomTypeId(existingRoomType.getRoomTypeId());
            pic.setRoomType(existingRoomType);
//            picRepository.save(pic);
        });
        picRepository.saveAll(existingRoomType.getRoomPics());
        return roomType;
    }

    //單一房型拿到文字資料
    @Override
    @Transactional
    public SingleHotelDTO getSingleHotel(Integer hotelId, Integer roomTypeId,
                                         LocalDate inDay,
                                         LocalDate outDay) {
        SingleHotelDTO singleHotelDTO = new SingleHotelDTO();
        HotelOwnerVO hotelOwnerVO = hotelOwnerRepository.getReferenceById(hotelId);
        singleHotelDTO.setHotelName(hotelOwnerVO.getHotelName());
        singleHotelDTO.setHotelAddress(hotelOwnerVO.getHotelAddress());
        RoomType roomType = typeRepository.getReferenceById(roomTypeId);
        singleHotelDTO.setRoomTypeAbout(roomType.getRoomTypeAbout());
        singleHotelDTO.setRoomTypePrice(roomType.getRoomTypePrice());
        singleHotelDTO.setRoomTypeName(roomType.getRoomTypeName());
        singleHotelDTO.setInDay(inDay);
        singleHotelDTO.setOutDay(outDay);
        return singleHotelDTO;
    }


    class RoomTypeNotFoundException extends RuntimeException {
        RoomTypeNotFoundException(Integer id) {
            super("找不到業主ID: " + id);
        }
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    //符合使用者條件的房型
    @Override
    public List<AllHotelDTO> searchHotels(searchHotelDTO searchDto) {
        List<RoomType> roomTypes = typeRepository.findBySearchCriteria(searchDto.getLocation(), searchDto.getPetType(), searchDto.getPetSize());

        // 將RoomType列表轉為AllHotelDTO列表
        List<AllHotelDTO> allHotelDTOs = roomTypes.stream().map(roomType -> {
            AllHotelDTO allHotelDTO = new AllHotelDTO();

            // 用hotelOwnerRepository拿HotelOwnerVO
            HotelOwnerVO hotelOwnerVO = hotelOwnerRepository.findById(roomType.getHotelId())
                    .orElseThrow(() -> new ResourceNotFoundException("HotelOwner not found with id " + roomType.getHotelId()));
            allHotelDTO.setHotelId(hotelOwnerVO.getHotelId());
            allHotelDTO.setRoomTypeId(roomType.getRoomTypeId());
            allHotelDTO.setHotelName(hotelOwnerVO.getHotelName());
            allHotelDTO.setHotelAddress(hotelOwnerVO.getHotelAddress());
            allHotelDTO.setRoomTypeName(roomType.getRoomTypeName());
            allHotelDTO.setRoomTypeAbout(roomType.getRoomTypeAbout());
            allHotelDTO.setRoomTypePrice(roomType.getRoomTypePrice());
            allHotelDTO.setReviewScoreTotal(hotelOwnerVO.getReviewScoreTotal());
            allHotelDTO.setInDay(searchDto.getInDay());
            allHotelDTO.setOutDay(searchDto.getOutDay());
            if (!roomType.getRoomPics().isEmpty()) {
                RoomPic firstRoomPic = roomType.getRoomPics().get(0);  // 拿第一張圖片
                byte[] roomPicBytes = firstRoomPic.getRoomPic();
                String encodedImage = Base64.getEncoder().encodeToString(roomPicBytes);
                String imageUrl = "data:image/*;base64," + encodedImage;
                List<String> roomPics = allHotelDTO.getRoomPics();
                roomPics.add(imageUrl);
                allHotelDTO.setRoomPics(roomPics);
            }

            return allHotelDTO;
        }).collect(Collectors.toList()); //把stream中的元素放到集合中

        return allHotelDTOs;
    }

    @Override
    public List<String> getRoomTypeImages(Integer roomTypeId) {
        RoomType roomType = typeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id " + roomTypeId));
        List<RoomPic> roomPics = roomType.getRoomPics();
        List<String> images = new ArrayList<>();
        for (RoomPic pic : roomPics) {
            byte[] roomPicBytes = pic.getRoomPic();
            String encodedImage = Base64.getEncoder().encodeToString(roomPicBytes);
            String imageUrl = "data:image/*;base64," + encodedImage;
            images.add(imageUrl);
        }
        return images;
    }

    public List<RoomReview> getReviewsByHotelId(Integer hotelId) {
        return roomReviewRepository.findByHotelId(hotelId);
    }
    @Override
    public RoomType getRoomTypeById(Integer roomTypeId) {
        Optional<RoomType> optionalRoomType = typeRepository.findById(roomTypeId);
        if (optionalRoomType.isPresent()) {
            return optionalRoomType.get();
        } else {
            throw new RuntimeException("RoomType not found for id :: " + roomTypeId);
        }
    }
}