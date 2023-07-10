package tw.idv.petradisespringboot.member.vo;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum MemberAccess {
    ACTIVE("0"), INACTIVE("1");

    private final String value;

    MemberAccess(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MemberAccess fromValue(String value) {
        return Stream.of(MemberAccess.values())
                .filter(memberAccess -> memberAccess.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
