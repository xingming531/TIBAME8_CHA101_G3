package tw.idv.petradisespringboot.chat.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.idv.petradisespringboot.chat.repo.ChatMessageRepository;
import tw.idv.petradisespringboot.chat.vo.ChatMessage;
import tw.idv.petradisespringboot.chat.vo.ChatMessage.ChatType;
import tw.idv.petradisespringboot.hotel_owner.repo.HotelOwnerRepository;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@Controller
public class GuestChatController {

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Autowired
	private HotelOwnerRepository hotelOwnerRepository;

	@GetMapping("/api/messages/guest")
	@ResponseBody
	public List<ChatMessage> getRecentMessages(@RequestParam String hotelId) {
		List<ChatMessage> messages = new ArrayList<>(chatMessageRepository.findAll());

		messages = messages.stream().filter(msg -> hotelId.equals(msg.getHotelId())).collect(Collectors.toList());
		System.out.println(messages);
		messages.sort(
				Comparator.comparing(message -> Optional.ofNullable(message.getTimestamp()).orElse(Instant.EPOCH)));
		return messages; // 返回排序后的messages，而不是findAll的结果
	}

	@GetMapping("/hotelName")
	@ResponseBody
	public List<HotelOwnerVO> getHotelName(@RequestParam String hotelId) {
		int hotelid = Integer.parseInt(hotelId);
		List<HotelOwnerVO> hotel = hotelOwnerRepository.findByhotelId(hotelid);
		System.out.println(hotel);
		return hotel;
	}

	// 處理訊息
	@MessageMapping("/guest-chat")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		String content = chatMessage.getContent();

		String username = chatMessage.getSender();

		String hotelId = chatMessage.getHotelId();
		System.out.println(hotelId);
		chatMessage.setSender(username);
		if (content != null) {
			chatMessage.setContent(content);
		}

		chatMessage.setTimestamp(Instant.now());
		chatMessage.setChatType(ChatType.CHAT);
		chatMessage.setHotelId(hotelId);
		chatMessageRepository.save(chatMessage);
		return chatMessage; // 返回時會將訊息送至/topic/public
	}

}
