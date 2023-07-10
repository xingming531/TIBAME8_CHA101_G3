package tw.idv.petradisespringboot.pet.vo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pet_pic")
public class PetPic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Lob
    @Column
    private byte[] pic;

    @Override
    public String toString() {
        return "PetPic{" +
                "id=" + id +
                ", pet=" + pet +
                '}';
    }
}
