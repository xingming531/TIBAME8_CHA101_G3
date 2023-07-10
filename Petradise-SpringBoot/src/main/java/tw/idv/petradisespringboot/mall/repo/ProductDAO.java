package tw.idv.petradisespringboot.mall.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.idv.petradisespringboot.mall.vo.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
