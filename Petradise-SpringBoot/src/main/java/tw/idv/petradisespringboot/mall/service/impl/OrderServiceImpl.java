package tw.idv.petradisespringboot.mall.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.mall.NotFoundException4OrderMaster;
import tw.idv.petradisespringboot.mall.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.dto.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.repo.OrderDetailRepository;
import tw.idv.petradisespringboot.mall.repo.OrderMasterRepository;
import tw.idv.petradisespringboot.mall.vo.OrderDetail;
import tw.idv.petradisespringboot.mall.vo.OrderDetailCompositePK;
import tw.idv.petradisespringboot.mall.vo.OrderMaster;
import tw.idv.petradisespringboot.mall.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderMasterRepository orderMasterRepository;
	private final OrderDetailRepository orderDetailRepository;

	OrderServiceImpl(OrderMasterRepository orderMasterRepository, OrderDetailRepository orderDetailRepository) {
		this.orderMasterRepository = orderMasterRepository;
		this.orderDetailRepository = orderDetailRepository;
	}

	@Transactional
	@Override
	public OrderMaster createOrder(CreateOrderDTO dto) {
		final var orderMaster = dto.getOrderMaster();
		final var savedOrderMaster = orderMasterRepository.save(orderMaster);
		final var products = dto.getProducts();
		final var orderMasterId = savedOrderMaster.getOdId();
		if (products != null) {
			products.forEach(product -> {

				final var productId = product.getProductId();

				final var productAmount = product.getProductAmount();

				// OrderDetail PK = OrderMaster ID + Product ID
				final var orderDetailPK = new OrderDetailCompositePK();
				orderDetailPK.setOdId(orderMasterId);
				orderDetailPK.setPdId(productId);

				var orderDetail = new OrderDetail();
				orderDetail.setId(orderDetailPK);
				orderDetail.setPdAmount(productAmount);
				orderDetailRepository.save(orderDetail);
			});
		}
		return savedOrderMaster;
	}

	@Override
	public List<AllOrderMasterDTO> findAllOrderMaster() {
		return orderMasterRepository.findAllOrderMaster();
	}

//	@Override
//	public List<OrderMaster> findOrderByMemberId(Integer memId) {
//		return orderMasterRepository.findByMemId(memId);
//	}
	
	@Override
	public List<OrderMaster> getByMemIdAndOdStatusNot(Integer memId, Character odStatus) {
		return orderMasterRepository.findByMemIdAndOdStatusNot(memId, odStatus);
	}

	@Override
	public List<OrderDetailDTO> findOrderDetailById(Integer id) {
		return orderDetailRepository.findOrderDetailById(id);
	}

	@Override
	public void updateOrderStatus(Integer odId, Character odStatus) {
		Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(odId);
		if (orderMasterOptional.isPresent()) {
			OrderMaster orderMaster = orderMasterOptional.get();
			orderMaster.setOdStatus(odStatus);
			orderMasterRepository.save(orderMaster);
		} else {
			throw new NotFoundException4OrderMaster("OrderMaster not found with id :" + odId);
		}
	}
	
}
