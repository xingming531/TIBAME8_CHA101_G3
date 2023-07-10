package tw.idv.petradisespringboot.mall.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

	@Column(name="sale_pro_id")
	private Integer saleProId;

	@Column(name="sale_pro_name")
	private String saleProName;

//	@Column(name="pd_type")
//	private String pdType;
	
	@Column(name = "pd_id")
	private Integer pdId;

	@Column(name="sale_discount")
	private Double saleDiscount;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Column(name="sale_pro_start")
	private Timestamp saleProStart;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Column(name="sale_pro_end")
	private Timestamp saleProEnd;

//	@JsonIgnore
//	@JsonProperty("product")
//	private Product product;
//
//	@JsonIgnore
//	@JsonProperty("saleProject")
//	private SaleProject saleProject;

}
