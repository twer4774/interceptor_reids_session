package walter.unit.interceptor_reids_session.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import walter.unit.interceptor_reids_session.annotation.Auth;

@Controller
public class InterceptorController {

    @Auth
    @GetMapping("/auth")
    public ResponseEntity auth(){
        return ResponseEntity.ok().body("auth 어노테이션 존재");
    }

    @GetMapping("/non-auth")
    public ResponseEntity nonAuth(){
        return ResponseEntity.ok().body("auth 어노테이션 없음");
    }
}
