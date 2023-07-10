package tw.idv.petradisespringboot.mall.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.mall.vo.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
	
//	@Query("SELECT new tw.idv.petradisespringboot.mall.dto.SaleDTO "
//			+ "(sp.saleProId, sp.saleProName, p.pdType, s.saleDiscount, sp.saleProStart, sp.saleProEnd) "
//			+ "FROM SaleProject sp, Sale s, Product p "
//			+ "JOIN sp."
//			)
//	List<SaleDTO> getAllSales();
}
