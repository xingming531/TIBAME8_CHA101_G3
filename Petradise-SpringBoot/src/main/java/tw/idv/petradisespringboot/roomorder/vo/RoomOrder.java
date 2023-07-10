package tw.idv.petradisespringboot.roomorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.idv.petradisespringboot.member.vo.Member;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "room_order")
public class RoomOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_order_id")
    private Integer id;
    @Column(name = "mem_id")
    private Integer memId;
    @Column(name = "room_type_id")
    private Integer roomTypeId;
    @Column(name = "room_id", insertable = false)
    private Integer roomId;
    @Column(name = "room_pet_name")
    private String petName;
    @Column(name = "room_order_date", insertable = false)
    private Date orderDate;
    @Column(name = "check_in_date")
    private LocalDate checkInDate;
    @Column(name = "check_out_date")
    private LocalDate checkOutDate;
    @Column(name = "room_order_status")
    private Character status;
    @Column(name = "room_payment_method")
    private Character payMethod;
    @Column(name = "room_price")
    private Integer price;
    @Column(name = "room_od_special_req")
    private String specialReq;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id", insertable = false, updatable = false)
    private Member member;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", insertable = false, updatable = false)
    private RoomType roomType;
}
