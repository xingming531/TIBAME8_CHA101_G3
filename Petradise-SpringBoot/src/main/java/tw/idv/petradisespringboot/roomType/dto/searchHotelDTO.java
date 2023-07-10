package tw.idv.petradisespringboot.roomType.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class searchHotelDTO {
    private String location;
    private String petType;
    private Character petSize;
    private LocalDate inDay;
    private LocalDate outDay;

}
