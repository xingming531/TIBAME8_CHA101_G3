package tw.idv.petradisespringboot.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {

	@EmbeddedId
	private OrderDetailCompositePK id;

	@Column(name = "sale_pro_id")
	private Integer saleProId;

	@Column(name = "pd_amount")
	private Integer pdAmount;

//	@JsonBackReference
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "od_id", referencedColumnName = "od_id", insertable = false, updatable = false)
//	private OrderMaster orderMaster;
//
//	@JsonBackReference
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "pd_id", referencedColumnName = "pd_id", insertable = false, updatable = false)
//	private Product product;

}
