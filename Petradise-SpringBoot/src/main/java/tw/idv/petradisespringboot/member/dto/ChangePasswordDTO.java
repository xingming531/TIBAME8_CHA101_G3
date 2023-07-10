package tw.idv.petradisespringboot.member.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    Integer id;
    String oldPassword;
    String newPassword;
}