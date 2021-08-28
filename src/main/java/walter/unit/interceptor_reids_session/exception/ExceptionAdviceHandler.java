package walter.unit.interceptor_reids_session.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class ExceptionAdviceHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthException.class)
    public ResponseEntity authException(walter.unit.interceptor_reids_session.exception.AuthException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("권한이 없습니다. " + e.getLocalizedMessage());
    }
}
