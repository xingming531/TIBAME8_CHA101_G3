package tw.idv.petradisespringboot.mall.vo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Embeddable
@Component
public class OrderDetailCompositePK implements Serializable {
	private static final long serialVersionUID = -783590819230352601L;

	@Column(name = "od_id")
	private Integer odId;
	
	@Column(name = "pd_id")
	private Integer pdId;

}
