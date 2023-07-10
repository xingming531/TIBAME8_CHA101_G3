package tw.idv.petradisespringboot.mall.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDTO {

	private Integer odId;
	private String name;
	private String pdName;
	private Integer pdAmount;
	private Integer priceOri;
	private Integer priceShip;
	private Integer priceOd;
	private Character odStatus;
	private Date odDate;
	private Character odPay;
	private Character odShip;	
	private String reciName;
	private String reciPhone;
	private String reciAdd;
	private String reciStore;
	
	
}
