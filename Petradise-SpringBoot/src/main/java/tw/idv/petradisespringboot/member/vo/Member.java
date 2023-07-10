package tw.idv.petradisespringboot.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Integer id;
    @Column(name = "mem_name")
    private String name;
    @Column(name = "mem_account")
    private String account;
    @Column(name = "mem_password")
    private String password;
    @Column(name = "mem_birthday")
    private Date birthday;
    @Column(name = "mem_phone")
    private String phone;
    @Column(name = "mem_email")
    private String email;
    @Column(name = "mem_address")
    private String address;
    @Column(name = "mem_access", insertable = false)
    private MemberAccess access = MemberAccess.ACTIVE;
    @Column(name = "mem_bonus", insertable = false)
    private Integer bonus = 0;
    @Column(name = "mem_is_email_verified", insertable = false)
    private Boolean isEmailVerified = false;

}
