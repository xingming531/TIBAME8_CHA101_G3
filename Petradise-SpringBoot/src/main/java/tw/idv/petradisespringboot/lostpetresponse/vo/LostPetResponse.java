package tw.idv.petradisespringboot.lostpetresponse.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lost_pet_response")
public class LostPetResponse {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "response_id")
	private Integer responseId;
	
	@ToString.Exclude
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "article_id")
	private LostPetArticle article;
	
	@Column(name = "response_content")
	private String responseContent;
	
	@Column(name = "response_time", insertable = false)
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp responseTime;

}

