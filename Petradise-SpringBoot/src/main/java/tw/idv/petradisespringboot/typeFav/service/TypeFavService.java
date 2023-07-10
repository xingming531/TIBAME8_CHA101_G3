package tw.idv.petradisespringboot.typeFav.service;

import tw.idv.petradisespringboot.roomType.dto.AllHotelDTO;
import tw.idv.petradisespringboot.typeFav.vo.TypeFav;

import java.util.List;

public interface TypeFavService {
    void addTypeFav(TypeFav typeFav) throws Exception;
    List<AllHotelDTO> getFavRoomTypes(Integer memberId);
    void removeTypeFav(Integer memberId, Integer roomTypeId);

}
