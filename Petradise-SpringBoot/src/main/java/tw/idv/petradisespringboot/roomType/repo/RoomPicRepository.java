package tw.idv.petradisespringboot.roomType.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.roomType.vo.RoomPic;

import java.util.List;


@Repository
public interface RoomPicRepository extends JpaRepository<RoomPic, Integer> {
    List<RoomPic> findByRoomType_RoomTypeId(Integer roomTypeId);
}
