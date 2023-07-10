
package tw.idv.petradisespringboot.admin.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.admin.controller.AdminDTO;
import tw.idv.petradisespringboot.admin.controller.UpdateAdminDTO;
import tw.idv.petradisespringboot.admin.repo.AccessFunctionRepository;
import tw.idv.petradisespringboot.admin.repo.AdminAccessRepository;
import tw.idv.petradisespringboot.admin.repo.AdminRepository;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;
import tw.idv.petradisespringboot.admin.vo.AdminAccess;
import tw.idv.petradisespringboot.admin.vo.AdminAccessId;
import tw.idv.petradisespringboot.admin.vo.enums.AdminStatus;
import tw.idv.petradisespringboot.admin.vo.enums.AdminTitle;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminAccessRepository adminAccessRepository;
    private final AccessFunctionRepository accessFunctionRepository;

    AdminServiceImpl(AdminRepository adminRepository, AdminAccessRepository adminAccessRepository, AccessFunctionRepository accessFunctionRepository){
        this.adminRepository = adminRepository;
        this.adminAccessRepository = adminAccessRepository;
        this.accessFunctionRepository = accessFunctionRepository;
    }

    @Transactional
    @Override
    public Admin add(AdminDTO dto) {
        var admin = dto.getAdmin();
        // Save the Admin entity first so that it has an ID
        var newAdmin = adminRepository.save(admin);
        var functionIds = dto.getFunctionId();
        if (functionIds != null) {
            for (var functionId : functionIds) {
                if (functionId != null) {
                    var access = new AdminAccess();
                    // Create the composite key
                    var accessId = new AdminAccessId(newAdmin.getId(), functionId);
                    access.setId(accessId);
                    access.setAdmin(newAdmin);
                    access.setAccessFunction(accessFunctionRepository.findById(functionId).orElse(null));
                    // Save the AdminAccess entity
                    var newAccess = adminAccessRepository.save(access);
                    var existingAccesses = newAdmin.getAccesses();
                    if (existingAccesses == null) {
                        existingAccesses = new ArrayList<>();
                    }
                    existingAccesses.add(newAccess);
                    newAdmin.setAccesses(existingAccesses);
                }
            }
        }
        return newAdmin;
    }

    @Transactional
    @Override
    public Admin modify(UpdateAdminDTO dto) {
        final var optionalAdmin = adminRepository
                .findById(dto.getId());
        if (optionalAdmin.isEmpty()) {
            throw new AdminNotFoundException(dto.getId());
        }
        // 先更新Admin
        final var admin = optionalAdmin.get();
        admin.setName(dto.getName());
        admin.setPassword(dto.getPassword());
        admin.setPhone(dto.getPhone());
        admin.setAddress(dto.getAddress());
        admin.setEmail(dto.getEmail());
        admin.setTitle(dto.getTitle());
        admin.setStatus(dto.getStatus());
        adminRepository.save(admin);
        // 更新AdminAccess

        // 刪除AdminID 的全部AdminAccess
        adminAccessRepository.deleteAllByAdminId(dto.getId());

        final var functionIds = dto.getFunctionIds();
        final var newAccesses = functionIds.stream().map(functionId -> {
            final var access = new AdminAccess();
            final var accessId = new AdminAccessId(dto.getId(), functionId);
            access.setId(accessId);
            access.setAdmin(admin);
            access.setAccessFunction(accessFunctionRepository.findById(functionId).orElse(null));
            return access;
        }).collect(Collectors.toList());
        adminAccessRepository.saveAll(newAccesses);
        admin.setAccesses(newAccesses);
        return admin;
    }

    @Override
    public Admin findById(Integer id) {
        return adminRepository.findById(id).orElseThrow(
                () -> new AdminNotFoundException(id)
        );
    }
    @Override
    public List<Admin> searchAdminsByName(String keyword) {
        return adminRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Admin> findAdminsByTitle(AdminTitle title) {
        return adminRepository.findAdminsByTitle(title);
    }

    @Override
    public List<Admin> findAdminsByStatus(AdminStatus status) {
        return adminRepository.findAdminsByStatus(status);
    }

    @Override
    public List<Admin> getAdminsByIdOrderByTitle() {
        Sort sortByTitle = Sort.by(Sort.Direction.ASC, "title");
        return adminRepository.findAll(sortByTitle);
    }

    @Override
    public List<Admin> getAdminsByIdOrderByStatusDesc() {
        Sort sortByStatus = Sort.by(Sort.Direction.DESC, "status");
        return adminRepository.findAll(sortByStatus);
    }
    @Override
    public Admin changeAdminTitle(Integer id, AdminTitle newTitle) {
        return adminRepository.findById(id).map(admin -> {
            admin.setTitle(newTitle);
            return adminRepository.save(admin);
        }).orElse(null);
    }
    @Override
    public Admin changeAdminStatus(Integer id, AdminStatus newStatus) {
        return adminRepository.findById(id).map(admin -> {
            admin.setStatus(newStatus);
            return adminRepository.save(admin);
        }).orElse(null);
    }

    @Override
    public Admin login(String account, String password) {
        var vo = adminRepository
                .findByAccountAndPassword(account, password)
                .orElseThrow(() -> new AdminNotFoundException("帳號或密碼錯誤"));
        if (Objects.equals(vo.getStatus(), AdminStatus.INACTIVE)) {
            throw new AdminNotFoundException("帳號已停用");
        }
        return vo;
    }
}


