package tw.idv.petradisespringboot.roomorder.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ManageRoomOrderDTO {
    private Integer id;
    private String memberName;
    private String petName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String roomTypeName;
    private Character status;

    public ManageRoomOrderDTO(Integer id, String memberName, String petName, LocalDate checkInDate, LocalDate checkOutDate, String roomTypeName, Character status) {
        this.id = id;
        this.memberName = memberName;
        this.petName = petName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomTypeName = roomTypeName;
        this.status = status;
    }
}
