package walter.unit.interceptor_reids_session.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import walter.unit.interceptor_reids_session.annotation.Auth;
import walter.unit.interceptor_reids_session.exception.AuthException;
import walter.unit.interceptor_reids_session.exception.ExceptionAdviceHandler;
import walter.unit.interceptor_reids_session.model.SessionMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link walter.unit.interceptor_reids_session.controller.InterceptorController}에서 동작하는 interceptor
 */
@Slf4j
@Component
public class SessionAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //어노테이션 체크 - Controller에 @Auth 어노테이션이 있는지 확인

        boolean hasAnnotation = checkAnnotation(handler, Auth.class);

        if (hasAnnotation) {

            //어노테이션이 있으면서, User의 정보가 맞다면 true 반환
            //request에서 session 받아오기
            HttpSession session = request.getSession();
            SessionMember sessionMember = (SessionMember) session.getAttribute("sessionMember");//sessionMember객체로 저장된 객체 반환
            String userName = sessionMember.getName();
            Integer userAge = sessionMember.getAge();

            log.info("userName, userAge : {}, {}", userName, userAge);

            //User의 정보는 DB에서 불러오지만, 여기서는 간단히 하기 위해 임의의 값으로 확인
            // walter와 20이 세션정보에 있을 때 권한이 있는것으로 가정

            if (userName.equals("walter") && userAge.equals(20)) {
                return true;
            }

            throw new AuthException();
        }

        //Auth를 실패하더라도 Controller를 실행하기 위해서는 true로 설정해야한다. ex)/session/add의 경우 walter/20 이 아닌 다른 값이 들어가도 실행되어야 한다.
        return true;
    }


    private boolean checkAnnotation(Object handler, Class<Auth> authClass) {
        //js. html 타입인 view 과련 파일들은 통과한다.(view 관련 요청 = ResourceHttpRequestHandler)
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //Auth anntotation이 있는 경우
        if (null != handlerMethod.getMethodAnnotation(authClass) || null != handlerMethod.getBeanType().getAnnotation(authClass)) {
            return true;
        }

        //annotation이 없는 경우
        return false;
    }


}
