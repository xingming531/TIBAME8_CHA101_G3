package tw.idv.petradisespringboot.mall.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.mall.dto.AddSaleDTO;
import tw.idv.petradisespringboot.mall.dto.SaleDTO;
import tw.idv.petradisespringboot.mall.vo.Sale;
import tw.idv.petradisespringboot.mall.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	private ModelMapper modelMapper;
	
	private final SaleService saleService;

	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}

	@GetMapping("/all")
	List<SaleDTO> getAllSales() {
		return saleService.getAllSales()
				.stream()
				.map(sale -> modelMapper.map(sale, SaleDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	ResponseEntity<SaleDTO> getSaleById(@PathVariable(name = "id") Integer id) {
		Sale sale = saleService.getSaleById(id);

		// convert entity to DTO
		SaleDTO saleResponse = modelMapper.map(sale, SaleDTO.class);

		return ResponseEntity.ok().body(saleResponse);
	}

	@PostMapping("/add")
	ResponseEntity<AddSaleDTO> addSale(@RequestBody AddSaleDTO addSaleDTO) {

		Sale saleRequest = modelMapper.map(addSaleDTO, Sale.class);
		Sale sale = saleService.addSale(saleRequest);

		AddSaleDTO saleResponse = modelMapper.map(sale, AddSaleDTO.class);

		return new ResponseEntity<AddSaleDTO>(saleResponse, HttpStatus.CREATED);
	}

	ResponseEntity<SaleDTO> updateSale(@PathVariable Integer id, @RequestBody SaleDTO saleDTO) {
		
		Sale saleRequest = modelMapper.map(saleDTO, Sale.class);
		Sale sale = saleService.updateSale(id, saleRequest);

		SaleDTO saleResponse = modelMapper.map(sale, SaleDTO.class);
		
		return ResponseEntity.ok().body(saleResponse);
	}

}
