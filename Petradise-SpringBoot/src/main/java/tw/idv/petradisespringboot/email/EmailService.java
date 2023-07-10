package tw.idv.petradisespringboot.email;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
}
