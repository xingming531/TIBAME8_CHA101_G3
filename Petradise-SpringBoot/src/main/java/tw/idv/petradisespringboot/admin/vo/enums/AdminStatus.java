package tw.idv.petradisespringboot.admin.vo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum AdminStatus {
    ACTIVE("0"), INACTIVE("1");

    private final String value;

    AdminStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AdminStatus getByValue(String value) {
        return Arrays.stream(values())
                .filter(adminStatus -> adminStatus.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
