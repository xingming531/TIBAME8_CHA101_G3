package tw.idv.petradisespringboot.chat.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.idv.petradisespringboot.chat.repo.ChatMessageRepository;
import tw.idv.petradisespringboot.chat.vo.ChatMessage;
import tw.idv.petradisespringboot.chat.vo.ChatMessage.ChatType;
import tw.idv.petradisespringboot.hotel_owner.repo.HotelOwnerRepository;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@Controller
public class ChatController {

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Autowired
	private HotelOwnerRepository hotelOwnerRepository;

	@GetMapping("/api/messages")
	@ResponseBody
	public List<ChatMessage> getRecentMessages(@RequestParam String hotelId) {
		System.out.println(hotelId);
		List<ChatMessage> messages = new ArrayList<>(chatMessageRepository.findAll());

		messages = messages.stream().filter(msg -> hotelId.equals(msg.getHotelId())).collect(Collectors.toList());
		messages.sort(
				Comparator.comparing(message -> Optional.ofNullable(message.getTimestamp()).orElse(Instant.EPOCH)));
		return messages;
	}

	// 處理訊息
	@MessageMapping("/chat")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		String content = chatMessage.getContent();

		String username = chatMessage.getSender();
		String hotelId = chatMessage.getHotelId();
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

//	業主聊天室
	@PostMapping("/login")
	public ResponseEntity<?> ownerLogin(@RequestParam String account, @RequestParam String password) {
		Optional<HotelOwnerVO> user = hotelOwnerRepository.findByOwnerAccountAndOwnerPassword(account, password);
		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("帳號或密碼錯誤");
		}
		return ResponseEntity.ok(user);

	}
}
