package tw.idv.petradisespringboot.pet.vo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.idv.petradisespringboot.pet.vo.enums.PetSize;
import tw.idv.petradisespringboot.pet.vo.enums.PetStatus;
import tw.idv.petradisespringboot.pet.vo.enums.PetType;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Integer id;
    @Column(name = "mem_id")
    private Integer memberId;
    @Column(name = "pet_name")
    private String name;
    @Column(name = "pet_type")
    private PetType type;
    @Column(name = "pet_size")
    private PetSize size;
    @Column(name = "pet_status", insertable = false)
    private PetStatus status = PetStatus.NORMAL;

    // The mappedBy attribute refers to the property name of the association on the owner side.
    @JsonManagedReference
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private List<PetPic> petPics;
}
