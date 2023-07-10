package tw.idv.petradisespringboot.admin.service;

import tw.idv.petradisespringboot.admin.controller.AdminDTO;
import tw.idv.petradisespringboot.admin.controller.UpdateAdminDTO;
import tw.idv.petradisespringboot.admin.vo.Admin;
import tw.idv.petradisespringboot.admin.vo.enums.AdminStatus;
import tw.idv.petradisespringboot.admin.vo.enums.AdminTitle;

import java.util.List;

public interface AdminService {

    Admin add(AdminDTO adminDTO);
    Admin modify(UpdateAdminDTO dto);
    Admin findById(Integer id);
    List<Admin> searchAdminsByName(String keyword);
    List<Admin> findAdminsByTitle(AdminTitle title);
    List<Admin> findAdminsByStatus(AdminStatus status);
    List<Admin> getAdminsByIdOrderByTitle();
    List<Admin> getAdminsByIdOrderByStatusDesc();
    Admin changeAdminTitle(Integer id, AdminTitle newTitle);
    Admin changeAdminStatus(Integer id, AdminStatus newStatus);

    Admin login(String account, String password);

}

