package tw.idv.petradisespringboot.pet.vo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum PetType {
    DOG("Dog"), CAT("Cat"), BIRD("Bird"), OTHER("Other");

    private final String value;

    PetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PetType fromValue(String value) {
        return Stream.of(PetType.values())
                .filter(petType -> petType.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.getValue();
    }
}
