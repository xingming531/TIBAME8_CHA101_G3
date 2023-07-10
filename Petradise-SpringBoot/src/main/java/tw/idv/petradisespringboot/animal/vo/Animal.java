package tw.idv.petradisespringboot.animal.vo;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tw.idv.petradisespringboot.animlpic.vo.AnimalPic;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "animal")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "animal_id")
	private Integer animalId;
	
	
	@Column(name = "corp_id")
	private Integer corpId;
	
	@Column(name = "mem_id")
	private Integer memId;
	
	@Column(name = "type")
	private String animalType;
	
	@Column(name = "animal_name")
	private String animalName;
	
	@Column(name = "animal_sex")
	private String animalSex;
	
	@Column(name = "animal_age")
	private String animalAge;
	
	@Column(name = "animal_pic",columnDefinition = "LONGBLOB")
	private byte[] animalPic;
	
	@Column(name = "status", insertable = false)
	private String aniamlStatus;
	
	@Column(name ="animal_info_note", insertable = false )
	private String animalInfo;
	

	
	



	

	
}