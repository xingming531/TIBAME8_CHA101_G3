package tw.idv.petradisespringboot.room.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "room")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roomId")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;


    @ManyToOne
    @JoinColumn(name = "room_type_id", insertable = true, updatable = true)
    private RoomType roomType;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_sale_status")
    private Character roomSaleStatus;

    @Column(name = "room_status")
    private Character roomStatus;

       @Override
    public String toString() {
        return "Room [roomId=" + roomId + ", roomType=" + roomType + ", petName=" + petName
                + ", roomName=" + roomName + ", roomSaleStatus=" + roomSaleStatus
                + ", roomStatus=" + roomStatus + "]";
    }

}
