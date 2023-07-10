package tw.idv.petradisespringboot.mall.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Embeddable
@Component
public class SaleCompositePK implements Serializable {
	private static final long serialVersionUID = 86256744017994317L;

	@Column(name = "pd_id")
	private Integer pdId;
	
	@Column(name = "sale_pro_id")
	private Integer saleProId;

}
