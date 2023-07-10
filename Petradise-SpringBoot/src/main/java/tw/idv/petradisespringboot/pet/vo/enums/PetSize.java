package tw.idv.petradisespringboot.pet.vo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum PetSize {
    SMALL("S"), MEDIUM("M"), LARGE("L");

    private final String value;

    PetSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PetSize fromValue(String value) {
        return Stream.of(PetSize.values())
                .filter(petSize -> petSize.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
