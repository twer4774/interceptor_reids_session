package walter.unit.interceptor_reids_session.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import walter.unit.interceptor_reids_session.interceptor.AuthInterceptor;
import walter.unit.interceptor_reids_session.interceptor.SessionAuthInterceptor;

@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    private final AuthInterceptor authInterceptor;
    private final SessionAuthInterceptor sessionAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor);
        registry.addInterceptor(sessionAuthInterceptor).addPathPatterns("/session/*");
    }
}
