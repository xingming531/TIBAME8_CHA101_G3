package tw.idv.petradisespringboot.mall.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.mall.vo.SaleProject;

@Repository
public interface SaleProjectRepository extends JpaRepository<SaleProject, Integer> {
}
