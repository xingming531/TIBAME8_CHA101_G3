package tw.idv.petradisespringboot.roomreview.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "room_review")
public class RoomReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_review_id")
    private Integer id;
    @Column(name = "hotel_id")
    private Integer hotelId;
    @Column(name = "room_order_id")
    private Integer roomOrderId;
    @Column(name = "room_review_score")
    private Integer score;
    @Column(name = "room_review_content")
    private String content;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_order_id", insertable = false, updatable = false)
    private RoomOrder roomOrder;


    @Override
    public String toString() {
        return "RoomReview{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", roomOrderId=" + roomOrderId +
                ", score=" + score +
                ", content='" + content + '\'' +
                '}';
    }
}