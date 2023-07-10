package tw.idv.petradisespringboot.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AllOrderMasterDTO {

	private Integer odId;
	private String name;
	private Date odDate;
	private Integer priceOd;
	private String reciName;
	private String reciPhone;
	private Character odStatus;
	
}
