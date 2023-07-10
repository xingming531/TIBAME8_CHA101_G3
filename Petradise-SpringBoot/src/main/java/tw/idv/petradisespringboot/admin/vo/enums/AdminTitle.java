package tw.idv.petradisespringboot.admin.vo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum AdminTitle {
    MANAGER("0"), FULL_TIME("1"), PART_TIME("2");

    private final String value;

    AdminTitle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static AdminTitle getByValue(String value) {
        return Arrays.stream(values())
                .filter(adminTitle -> adminTitle.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
