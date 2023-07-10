package tw.idv.petradisespringboot.member.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UpdateDTO {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Date birthday;
}
