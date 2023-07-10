package tw.idv.petradisespringboot.member.exceptions;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Integer id) {
        super("找不到會員ID: " + id);
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
