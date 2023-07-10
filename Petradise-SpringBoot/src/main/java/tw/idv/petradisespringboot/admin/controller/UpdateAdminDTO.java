package tw.idv.petradisespringboot.admin.controller;

import lombok.Data;
import tw.idv.petradisespringboot.admin.vo.enums.AdminStatus;
import tw.idv.petradisespringboot.admin.vo.enums.AdminTitle;

import java.util.List;

@Data
public class UpdateAdminDTO {
    private Integer id;
    private String name;
    private String password;
    private String phone;
    private String address;
    private String email;
    private AdminTitle title;
    private AdminStatus status;

    private List<Integer> functionIds;
}
