package com.hm.userservice.global.config;

import com.hm.userservice.domain.constants.CompanyPosition;
import com.hm.userservice.global.Interceptor.LogInterceptor;
import com.hm.userservice.global.Interceptor.SessionAuthenticationInterceptor;
import com.hm.userservice.global.Interceptor.FindAuthorizationInterceptor;
import com.hm.userservice.service.find.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final FindService findService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Log Interceptor
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/*.ico","/**/*swagger*/**","/webjars/**");

        //Authorization Interceptor
        registry.addInterceptor(new SessionAuthenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/css/**","/*.ico","/error","/login","/join",
                        "/**/*swagger*/**","/webjars/**")
                .order(1);

        registry.addInterceptor(new FindAuthorizationInterceptor(findService))
                .addPathPatterns("/find/**")
                .order(2);
    }
}
