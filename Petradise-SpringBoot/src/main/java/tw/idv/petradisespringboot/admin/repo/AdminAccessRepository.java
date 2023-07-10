package tw.idv.petradisespringboot.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.admin.vo.AdminAccess;
import tw.idv.petradisespringboot.admin.vo.AdminAccessId;

@Repository
public interface AdminAccessRepository extends JpaRepository<AdminAccess, AdminAccessId> {
    void deleteAllByAdminId(Integer adminId);
}
