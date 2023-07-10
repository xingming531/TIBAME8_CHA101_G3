package tw.idv.petradisespringboot.mall.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")
public class Sale {

	@EmbeddedId
	private SaleCompositePK id;
	
	@Column(name = "sale_discount")
	private Double saleDiscount;
	
    @ManyToOne
    @JoinColumn(name = "pd_id", insertable = false, updatable=false)
    private Product product;
	
    @JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sale_pro_id", insertable = false, updatable=false)
	private SaleProject saleProject;
	
}
