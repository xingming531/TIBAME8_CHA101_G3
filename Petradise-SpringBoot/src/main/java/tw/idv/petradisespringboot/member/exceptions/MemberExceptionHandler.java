package tw.idv.petradisespringboot.member.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class MemberExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            MemberNotFoundException.class,
            ChangePasswordException.class
    })
    public ResponseEntity<?> handleMemberNotFoundException(
            Exception ex,
            WebRequest request
    ) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<?> handleAccountAlreadyExistsException(
            Exception ex,
            WebRequest request
    ) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
            LoginException.class,
            MemberNotVerifiedException.class,
            VerificationException.class,
    })
    public ResponseEntity<?> handleLoginException(
            Exception ex,
            WebRequest request
    ) {
        return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<?> handleUnknownException(
            Exception ex,
            WebRequest request
    ) {
        return buildErrorResponse("伺服器出現未知錯誤，請通知開發人員", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> buildErrorResponse(Exception ex, HttpStatus status) {
        return buildErrorResponse(ex.getMessage(), status);
    }
     private ResponseEntity<?> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }


}
