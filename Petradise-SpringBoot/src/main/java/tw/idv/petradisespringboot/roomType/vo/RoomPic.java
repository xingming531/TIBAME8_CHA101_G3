package tw.idv.petradisespringboot.roomType.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name ="room_pic")
public class RoomPic implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_pic_id")
	private Integer roomPicId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "room_type_id", insertable = false, updatable = false)
	private RoomType roomType;

	
	@Column(name = "room_pic")
	private byte[] roomPic;

	@Column(name = "room_type_id")
	private Integer roomTypeId;

}
