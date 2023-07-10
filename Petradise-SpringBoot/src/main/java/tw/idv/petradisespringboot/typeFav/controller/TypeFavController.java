package tw.idv.petradisespringboot.typeFav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.roomType.dto.AllHotelDTO;
import tw.idv.petradisespringboot.typeFav.service.TypeFavService;
import tw.idv.petradisespringboot.typeFav.vo.TypeFav;

import java.util.List;

@RestController
@RequestMapping("/roomType")
public class TypeFavController {
    @Autowired
    private TypeFavService typeFavService;

    @PostMapping("/favs")
    public ResponseEntity<?> addTypeFav(@RequestBody TypeFav typeFav) {
        try {
            typeFavService.addTypeFav(typeFav);
            return new ResponseEntity<>("收藏成功！", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/favs/{memberId}")
    public ResponseEntity<?> getFavRoomTypes(@PathVariable Integer memberId) {
        try {
            List<AllHotelDTO> allHotelDTOs = typeFavService.getFavRoomTypes(memberId);
            return new ResponseEntity<>(allHotelDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/favs")
    public ResponseEntity<?> removeTypeFav(@RequestParam Integer memberId, @RequestParam Integer roomTypeId) {
        try {
            typeFavService.removeTypeFav(memberId, roomTypeId);
            return new ResponseEntity<>("取消收藏成功！", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

