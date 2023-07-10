package tw.idv.petradisespringboot.lostpetarticle.vo;

import java.util.Base64;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lost_pet_pic")
public class LostPetPic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lost_pet_pic_id")
	private Integer lostPetPicId;
	
	@ToString.Exclude
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "article_id")
	private LostPetArticle article;
	
	@Lob
	@Column(name = "lost_pet_pic", columnDefinition = "LONGBLOB")
	private byte[] lostPetPic;
	
	
	
}
