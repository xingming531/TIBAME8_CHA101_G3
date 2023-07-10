package tw.idv.petradisespringboot.hotel_owner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.petradisespringboot.hotel_owner.dto.LoginDTO;
import tw.idv.petradisespringboot.hotel_owner.service.HotelOwnerService;

@RestController
@RequestMapping("/ownerLogin")
public class OwnerLoginController {

    private final HotelOwnerService hotelOwnerService;

    public OwnerLoginController(HotelOwnerService hotelOwnerService) {
        this.hotelOwnerService = hotelOwnerService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            return ResponseEntity.ok(hotelOwnerService.login(dto.getAccount(), dto.getPassword()));
        } catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }
}
