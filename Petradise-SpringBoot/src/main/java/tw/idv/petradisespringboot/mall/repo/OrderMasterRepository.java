package tw.idv.petradisespringboot.mall.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.mall.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.vo.OrderMaster;

import java.util.List;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {
	
    @Query("SELECT new tw.idv.petradisespringboot.mall.dto.AllOrderMasterDTO(" +
            "om.odId, " +
            "m.name, " +
            "om.odDate, " +
            "om.priceOd, " +
            "om.reciName, " +
            "om.reciPhone, " +
            "om.odStatus) " +
            "FROM OrderMaster om " +
            "JOIN Member m ON m.id = om.memId")
    List<AllOrderMasterDTO> findAllOrderMaster();
    
//    List<OrderMaster> findByMemId(Integer memId);
    
    List<OrderMaster> findByMemIdAndOdStatusNot(Integer memId, Character odStatus);

    @Modifying
    @Query("UPDATE OrderMaster o SET o.odStatus = :odStatus WHERE o.odId = :odId")
    void updateOrderStatus(@Param("odId")Integer odId, 
    					   @Param("odStatus") Character odStatus);
}
