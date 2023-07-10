package tw.idv.petradisespringboot.member.exceptions;

public class ChangePasswordException extends RuntimeException {
    public ChangePasswordException(String message) {
        super(message);
    }
}
