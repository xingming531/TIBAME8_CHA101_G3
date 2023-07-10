package tw.idv.petradisespringboot.mall.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.mall.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.vo.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	
    @Query("SELECT new tw.idv.petradisespringboot.mall.dto.OrderDetailDTO(" +
    		"od.id.odId, " +
            "m.name, " +
            "p.pdName, " +
            "od.pdAmount, " +
            "om.priceOri," +
            "om.priceShip," +
            "om.priceOd," +
            "om.odStatus, " +
            "om.odDate, " +
            "om.odPay," +
            "om.odShip," +
            "om.reciName, " +
            "om.reciPhone, " +
            "om.reciAdd, " +
            "om.reciStore) " +
            "FROM OrderDetail od " +
            "JOIN OrderMaster om ON om.odId = od.id.odId " +
            "JOIN Member m ON m.id = om.memId " +
            "JOIN Product p ON od.id.pdId = p.pdId " +
            "WHERE od.id.odId = :orderId")
    List<OrderDetailDTO> findOrderDetailById(@Param("orderId") Integer orderId);
}
