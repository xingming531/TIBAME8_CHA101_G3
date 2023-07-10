package tw.idv.petradisespringboot.member.dto;

import lombok.Data;
import tw.idv.petradisespringboot.member.vo.MemberAccess;

import java.sql.Date;

@Data
public class MemberDTO {
    private Integer id;
    private String name;
    private String account;
    private Date birthday;
    private String phone;
    private String email;
    private String address;
    private MemberAccess access;
    private Integer bonus;
    private Boolean isEmailVerified;
}
