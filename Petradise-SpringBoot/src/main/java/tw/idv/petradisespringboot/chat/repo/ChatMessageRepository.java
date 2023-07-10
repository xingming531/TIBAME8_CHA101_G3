package tw.idv.petradisespringboot.chat.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.chat.vo.ChatMessage;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {
	List<ChatMessage> findAll();

	List<ChatMessage> findAll(Sort sort);

	List<ChatMessage> findByHotelId(String hotelId);

	List<HotelOwnerVO> getOwnerName();
}