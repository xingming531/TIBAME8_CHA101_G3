package tw.idv.petradisespringboot.mall.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.mall.NotFoundException4OrderMaster;
import tw.idv.petradisespringboot.mall.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.dto.ChangeOrderStatusDTO;
import tw.idv.petradisespringboot.mall.dto.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.vo.OrderMaster;
import tw.idv.petradisespringboot.mall.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;

	OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/allOrder")
	public List<AllOrderMasterDTO> findAllOrderMaster() {
		return orderService.findAllOrderMaster();
	}

//    @GetMapping("/memberId={memberId}")
//    public List<OrderMaster> findOrdersByMemberId(@PathVariable Integer memberId) {
//        return orderService.findOrderByMemberId(memberId);
//    }

	@GetMapping("/memberIdAndOrderdStatusNot={memberId}")
	public List<OrderMaster> getByMemIdAndOdStatusNot(@PathVariable Integer memberId) {
		Character orderStatus = '1';
		return orderService.getByMemIdAndOdStatusNot(memberId, orderStatus);
	}

	@GetMapping("/showOrderDetail/id={id}")
	public List<OrderDetailDTO> findOrderDetailById(@PathVariable Integer id) {
		return orderService.findOrderDetailById(id);
	}

	@PutMapping(value = "/add", consumes = { "application/json" }) // default. Cuz using REST API.
	public ResponseEntity<?> addOrder(@RequestBody CreateOrderDTO dto) {
		return ResponseEntity.ok(orderService.createOrder(dto));
	}

	@PostMapping("/updateOrderStatus")
	public ResponseEntity<?> updateOrderStatus(@RequestBody ChangeOrderStatusDTO dto) {
		try {
			orderService.updateOrderStatus(dto.getOdId(), dto.getOdStatus());
			return ResponseEntity.ok("Order status updated successfully!");
		} catch (NotFoundException4OrderMaster exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

}
