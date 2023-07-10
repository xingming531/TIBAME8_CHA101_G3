package tw.idv.petradisespringboot.member.exceptions;

public class MemberNotVerifiedException extends RuntimeException{
    public MemberNotVerifiedException() {
        super("該帳號尚未驗證");
    }

    public MemberNotVerifiedException(String message) {
        super(message);
    }
}
