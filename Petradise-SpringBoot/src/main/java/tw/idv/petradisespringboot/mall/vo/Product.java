package tw.idv.petradisespringboot.mall.vo;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pd_id")
    Integer pdId;
    @Column(name = "pd_type")
    String pdType;
    @Column(name = "pd_pet_type")
    String pdPetType;
    @Column(name = "pd_name")
    String pdName;
    @Column(name = "pd_price")
    Integer pdPrice;
    @Column(name = "pd_info")
    String pdInfo;
    @Column(name = "pd_status")
    Character pdStatus;
    @Column(name = "pd_date")
    Date pdDate;
    @Column(name = "pd_rank")
    Double pdRank;
    @Column(name = "pd_img")
    @Lob
    byte[] pdImg;

}
