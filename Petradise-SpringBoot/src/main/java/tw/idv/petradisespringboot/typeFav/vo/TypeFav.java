package tw.idv.petradisespringboot.typeFav.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "fav_list")
public class TypeFav {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fav_list_id")
    private Integer id;

    @Column(name = "mem_id")
    private Integer memId;

    @Column(name = "room_type_id")
    private Integer roomTypeId;


}
