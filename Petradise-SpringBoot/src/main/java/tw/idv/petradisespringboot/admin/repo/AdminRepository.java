
package tw.idv.petradisespringboot.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.admin.vo.Admin;
import tw.idv.petradisespringboot.admin.vo.enums.AdminStatus;
import tw.idv.petradisespringboot.admin.vo.enums.AdminTitle;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Integer> {

    List<Admin> findByNameContainingIgnoreCase(String keyword);
    List<Admin> findAdminsByTitle(AdminTitle title);
    List<Admin> findAdminsByStatus(AdminStatus status);

    Optional<Admin> findByAccountAndPassword(String account, String password);

}

