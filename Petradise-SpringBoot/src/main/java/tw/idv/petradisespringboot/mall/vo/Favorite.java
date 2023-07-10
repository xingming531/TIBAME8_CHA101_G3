package tw.idv.petradisespringboot.mall.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorite")
public class Favorite {
	
	@EmbeddedId
	private FavoriteCompositePK id;
	
	@Column(name = "fav_date")
	private Date favDate;
	
}
