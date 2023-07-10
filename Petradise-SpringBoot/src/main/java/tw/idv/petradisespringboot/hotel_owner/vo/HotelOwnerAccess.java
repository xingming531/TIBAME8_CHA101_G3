package tw.idv.petradisespringboot.hotel_owner.vo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HotelOwnerAccess {
	NORMAL("0"), SUSPENDED("1"), NOT_VERIFIED("2");

	private final String value;

	HotelOwnerAccess(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static HotelOwnerAccess fromValue(String value) {
		switch (value) {
		case "0":
			return NORMAL;
		case "1":
			return SUSPENDED;
		case "2":
			return NOT_VERIFIED;
		default:
			throw new IllegalArgumentException("No such value: " + value);
		}
	}

	@JsonValue
	@Override
	public String toString() {
		return this.value;
	}
}
