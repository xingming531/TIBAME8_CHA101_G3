package tw.idv.petradisespringboot.mall.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Embeddable
@Component
public class FavoriteCompositePK implements Serializable {
	private static final long serialVersionUID = -7337370425866004595L;

	@Column(name = "pd_id")
	private Integer pdId;
	
	@Column(name = "mem_id")
	private Integer memId;

}
