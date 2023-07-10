package tw.idv.petradisespringboot.mall.service;

import java.util.List;

import tw.idv.petradisespringboot.mall.vo.Sale;

public interface SaleService {
	
	List<Sale> getAllSales();

	Sale addSale(Sale sale);

	Sale updateSale(Integer id, Sale sale);

	Sale getSaleById(Integer id);

//	Sale deleteSale(Integer id);
	
}
