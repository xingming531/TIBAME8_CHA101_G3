package tw.idv.petradisespringboot.animalapplication.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "adopted_application")
public class AdoptedApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adopted_id")
    private Integer adoptedId;
    
    @Column(name = "mem_id")
    private Integer membId;
    
    @Column(name = "animal_id")
    private Integer animalId;

    @Column(name = "adopter_id_number")
    private String adopterIdNumber;

    @Column(name = "adopter_name")
    private String adopterName;

    @Column(name = "adopter_address")
    private String adopterAddress;

    @Column(name = "adopter_phone")
    private String adopterPhone;
    
    @Column(name = "adopter_job")
    private String adopterJob;
    
    @Column(name = "adopter_email")
    private String adopterEmail;

    @Column(name = "status", insertable = false )
    private String adopterStatus;

    @Column(name = "adopter_note", insertable = false)
    private String adopterNote;

    
	



	


	
	
	
	
	

}
