package tw.idv.petradisespringboot.chat.vo;

import java.time.Instant;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("chatMessage")
public class ChatMessage {

	@Id
	private String id;
	private ChatType type;
	/** 訊息發送者的名稱 */
	private String sender;
	/** 訊息內容 */
	private String content;
	private Instant timestamp;
	private String hotelId;

	/**
	 * 訊息種類Enum
	 */
	public enum ChatType {
		CHAT,

		LEAVE
	}

	public void setChatType(ChatType type) {
		this.type = type;
	}

	public ChatType getChatType() {
		return this.type;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSender() {
		return this.sender;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

}
