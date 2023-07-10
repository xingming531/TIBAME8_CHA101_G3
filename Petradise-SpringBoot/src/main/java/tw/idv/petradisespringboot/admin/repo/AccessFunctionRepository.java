package tw.idv.petradisespringboot.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.admin.vo.AccessFunction;

@Repository
public interface AccessFunctionRepository extends JpaRepository<AccessFunction, Integer> {
}
