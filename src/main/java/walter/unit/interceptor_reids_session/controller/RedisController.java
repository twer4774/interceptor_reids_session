package walter.unit.interceptor_reids_session.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class RedisController {


    @GetMapping("/redis-session")
    public ResponseEntity redisSession(HttpSession httpSession){
        return ResponseEntity.ok().body("session : " + httpSession);
    }
}
