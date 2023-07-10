package tw.idv.petradisespringboot.animlpic.vo;

import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Animal_pic")
public class AnimalPic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pic_id")
	private Integer picid;
	
	@Column(name ="animal_id" )
	private Integer animalid;
	
	@Column(name = "animal_pic",columnDefinition = "LONGBLOB")
	private byte[] animalpic;
	
	
	
	@Override
    public String toString() {
        return "Animal_pic [picid=" + picid + ", animal_id=" + animalid + ", animal_pic=" +animalpic + "]";
    }


}
