package tw.idv.petradisespringboot.pet.vo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum PetStatus {
    NORMAL("0"), DELETED("1");

    private final String value;

    PetStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PetStatus fromValue(String value) {
        return Stream.of(PetStatus.values())
                .filter(petStatus -> petStatus.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
