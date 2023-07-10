package tw.idv.petradisespringboot.roomType.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class SingleHotelDTO {
    private String hotelName;
    private String hotelAddress;
    private String roomTypeName;
    private String roomTypeAbout;
    private Integer roomTypePrice;
    private LocalDate inDay;
    private LocalDate outDay;
}
