package walter.unit.interceptor_reids_session.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AuthException extends RuntimeException{

    public AuthException(){
        super(HttpStatus.UNAUTHORIZED.toString());
    }

}
