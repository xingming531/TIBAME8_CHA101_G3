package tw.idv.petradisespringboot.mall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.mall.repo.SaleRepository;
import tw.idv.petradisespringboot.mall.vo.Sale;
import tw.idv.petradisespringboot.mall.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {

//	private final ModelMapper modelMapper;
//	private final SaleProjectRepository saleProjectRepository;
	private final SaleRepository saleRepository;
//	private final ProductDAO productDAO;

	public SaleServiceImpl(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}
//	public SaleServiceImpl(ModelMapper modelMapper, SaleProjectRepository saleProjectRepository,
//			SaleRepository saleRepository, ProductDAO productDAO) {
//		this.modelMapper = modelMapper;
//		this.saleProjectRepository = saleProjectRepository;
//		this.saleRepository = saleRepository;
//		this.productDAO = productDAO;
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 嚴格
//	}

	@Override
	public Sale addSale(Sale sale) {
		return saleRepository.save(sale);
		
//		Sale sale = modelMapper.map(saleDTO, Sale.class);
//		SaleProject saleProject = saleProjectRepository.findById(saleDTO.getSaleProId())
//				.orElseThrow(() -> new EntityNotFoundException("SaleProject not found."));
//		Product product = productDAO.findById(saleDTO.getSaleProId())
//				.orElseThrow(() -> new EntityNotFoundException("Product not found."));
//
//		SaleCompositePK saleId = new SaleCompositePK(product.getPdId(), saleProject.getSaleProId());
//		
//		sale.setId(saleId);
//		
//		return saleRepository.save(sale);
	}

	@Override
	public Sale updateSale(Integer id, Sale saleRequest) {
		Sale sale = saleRepository.findById(id)
				.orElseThrow();
		sale.setId(saleRequest.getId());
		sale.setSaleDiscount(saleRequest.getSaleDiscount());
		return saleRepository.save(sale);
	}
//	public Boolean updateSaleProject(SaleDTO saleProjectDTO) {
//		Optional<SaleProject> optionalSaleProject = saleProjectRepository.findById(saleProjectDTO.getSaleProId());
//		if (optionalSaleProject.isPresent()) {
//			SaleProject saleProject = optionalSaleProject.get();
//			saleProject.setSaleProName(saleProjectDTO.getSaleProName());
//			saleProject.setSaleProStart(saleProjectDTO.getSaleProStart());
//			saleProject.setSaleProEnd(saleProjectDTO.getSaleProEnd());
//
//			saleProjectRepository.save(saleProject);
//			return true;
//		} else {
//			return false;
//		}
//	}

	@Override
	public Sale getSaleById(Integer saleProId) {
		return saleRepository
				.findById(saleProId)
				.orElseThrow(() -> new SaleProjectNotFoundException(saleProId));
	}

	@Override
	public List<Sale> getAllSales() {
		return saleRepository.findAll();
//		return saleRepository.findAll()
//				.stream()
//				.map(this::EntityToDTO)
//				.collect(Collectors.toList());
	}
	
//	public SaleDTO EntityToDTO(Sale sale) {
//		return modelMapper.map(sale, SaleDTO.class);
//	}

}

class SaleProjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6301165073809004299L;

	SaleProjectNotFoundException(Integer saleProId) {
		super("SaleProject not found, id: " + saleProId);
	}
}
