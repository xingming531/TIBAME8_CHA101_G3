package tw.idv.petradisespringboot.mall.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_project")
public class SaleProject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_pro_id")
	private Integer saleProId;

	@Column(name = "sale_pro_name")
	private String saleProName;

	@Column(name = "sale_pro_start")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp saleProStart;

	@Column(name = "sale_pro_end")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp saleProEnd;
	
	@OneToMany(mappedBy = "saleProject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Sale> sale;

}
