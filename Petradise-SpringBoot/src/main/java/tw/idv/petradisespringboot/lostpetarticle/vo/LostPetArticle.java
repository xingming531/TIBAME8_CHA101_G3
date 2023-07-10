package tw.idv.petradisespringboot.lostpetarticle.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lost_pet_article")
public class LostPetArticle implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="article_id")
	private Integer articleId;
	
	@Column	(name="mem_id")
	private Integer memId;
	
	@Column (name="article_update", insertable = false, updatable=false)
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date update;
	
	@Column (name="lost_date")
	private Date lostDate;
	
	@Column	(name="lost_place")
	private String lostPlace;
	
	@Column	(name="chip_num")
	private String chipNum;
	
	@Column
	private String species;
	
	@Column
	private String color;
	
	@Column
	private String feature;
	
	@Column
	private String text;
	
	@Column	(name="contact_phone")
	private String contactPhone;
	
	@Column (name="article_status", insertable = false)
	private String articleStatus;
	
	@Column
	private String title;
	
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true) 
	private List<LostPetPic> lostPetPic;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
	private List<LostPetResponse> lostPetResponse;
	
	
}
