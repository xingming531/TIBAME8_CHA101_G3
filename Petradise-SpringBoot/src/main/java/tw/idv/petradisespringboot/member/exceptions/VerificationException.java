package tw.idv.petradisespringboot.member.exceptions;

public class VerificationException extends RuntimeException{
    public VerificationException(String message) {
        super(message);
    }
}
