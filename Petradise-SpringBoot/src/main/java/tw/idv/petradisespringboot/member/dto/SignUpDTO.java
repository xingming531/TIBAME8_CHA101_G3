package tw.idv.petradisespringboot.member.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class SignUpDTO {
    private String name;
    private String account;
    private String password;
    private Date birthday;
    private String phone;
    private String email;
    private String address;
}
