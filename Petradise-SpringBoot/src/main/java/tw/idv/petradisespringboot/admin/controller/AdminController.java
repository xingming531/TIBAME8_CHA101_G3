package tw.idv.petradisespringboot.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.admin.dto.LoginDTO;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;
import tw.idv.petradisespringboot.admin.vo.enums.AdminStatus;
import tw.idv.petradisespringboot.admin.vo.enums.AdminTitle;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;
    public AdminController(AdminService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public Admin addAdmin(@RequestBody AdminDTO adminDTO) {
        return service.add(adminDTO);
    }
    @PutMapping("/modify")
    Admin modifyAdmin(@RequestBody UpdateAdminDTO dto) {
        return service.modify(dto);
    }
    @GetMapping("/id/{id}")
    Admin findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping("/search/name/{keyword}")
    public List<Admin> searchAdminsByName(@PathVariable String keyword) {
        return service.searchAdminsByName(keyword);
    }
    @GetMapping("/title/{title}")
    List<Admin> findByTitle(@PathVariable String title){
        return service.findAdminsByTitle(AdminTitle.getByValue(title));
    }
    @GetMapping("/status/{status}")
    List<Admin> findByStatus(@PathVariable String status){
        return service.findAdminsByStatus(AdminStatus.getByValue(status));
    }
    @GetMapping("/all")
    List<Admin> getAdminsByIdOrderByTitle(){
        return service.getAdminsByIdOrderByTitle();
    }
    @GetMapping("/all/status/desc")
    List<Admin> getAdminsByIdOrderByStatusDesc(){
        return service.getAdminsByIdOrderByStatusDesc();
    }
    @PutMapping("/id/{id}/change-title")
    Admin changeAdminTitle(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
        var newTitle = AdminTitle.getByValue(requestBody.get("title"));
        return service.changeAdminTitle(id, newTitle);
    }
    @PutMapping("/id/{id}/change-status")
    Admin changeAdminStatus(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
        var newStatus = AdminStatus.getByValue(requestBody.get("status"));
        return service.changeAdminStatus(id, newStatus);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            var admin = service.login(dto.getAccount(), dto.getPassword());
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

