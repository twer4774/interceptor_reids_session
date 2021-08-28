package walter.unit.interceptor_reids_session.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import walter.unit.interceptor_reids_session.annotation.Auth;
import walter.unit.interceptor_reids_session.model.SessionMember;
import walter.unit.interceptor_reids_session.model.User;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class SessionInterceptorController {

    @GetMapping("/session")
    public String index(){
        return "sessionAdd";
    }

    @PostMapping("/session/add")
    public ResponseEntity add(@RequestBody User user, HttpSession session){
        log.info("user: {}", user);
        SessionMember sessionMember = new SessionMember();
        sessionMember.setName(user.getName());
        sessionMember.setAge(user.getAge());

        session.setAttribute("sessionMember", sessionMember);

        return ResponseEntity.ok().body("session add");
    }

    @Auth
    @GetMapping("/session/auth")
    public String sessionAuth(HttpSession httpSession, Model model){
        model.addAttribute("userData", (SessionMember)httpSession.getAttribute("sessionMember"));
        return "sessionResult";
    }

    @GetMapping("/session/non-auth")
    public String sessionNonAuth(HttpSession httpSession) {
        return "sessionResult";
    }

    @GetMapping("/session/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "sessionAdd";
    }
}
