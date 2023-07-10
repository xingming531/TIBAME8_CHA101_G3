package tw.idv.petradisespringboot.member.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_email_verification")
public class EmailVerification {

    @Id
    @Column(name = "mem_id")
    private Integer memberId;

    private String token;
}
