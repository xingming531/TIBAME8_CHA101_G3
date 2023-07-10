package tw.idv.petradisespringboot.roomType.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AllHotelDTO {
    private Integer hotelId;
    private Integer roomTypeId;
    private String hotelName;
    private String hotelAddress;
    private String roomTypeName;
    private String roomTypeAbout;
    private Integer roomTypePrice;
    private Integer reviewScoreTotal;
    private List<String> roomPics = new ArrayList<>();
    private LocalDate inDay;
    private LocalDate outDay;
}
