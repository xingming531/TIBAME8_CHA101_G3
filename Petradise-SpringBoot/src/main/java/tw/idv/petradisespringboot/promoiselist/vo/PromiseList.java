package tw.idv.petradisespringboot.promoiselist.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Table(name = "promise_list")
public class PromiseList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promise_id")
	private Integer promiseid;
	
	@Column(name = "mem_id")
	private Integer memid;
	
	@Column(name ="animal_id")
	private Integer animalid;
	
	@Column(name ="promise_time")
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date promisetime ;
	
	@Column(name = "promise_status",insertable = false)
	private String promisestatus;
	
	public String toString() {
		return "Animal [promiseID="+promiseid +",menId="+ memid +"animalId=" +animalid +"promiseStatus=" +promisestatus
				 + "]";
				
	}
	
	
	
	
	
}
