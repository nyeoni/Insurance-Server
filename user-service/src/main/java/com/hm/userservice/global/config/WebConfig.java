package com.hm.userservice.global.config;

import com.hm.userservice.global.Interceptor.LogInterceptor;
import com.hm.userservice.global.Interceptor.SessionAuthenticationInterceptor;
import com.hm.userservice.global.Interceptor.FindAuthorizationInterceptor;
import com.hm.userservice.service.find.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final FindService findService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //로그
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrl("/csrf"));
        //게이트웨이에서 처리하도록 바꿔야함
//        //로그인 사용자 여부 판별
//        registry.addInterceptor(new SessionAuthenticationInterceptor())
//                .addPathPatterns("/user/**")
//                .excludePathPatterns(excludeUrl("/login","/join","/logout"))
//                .order(1);
//        //조회 권한 여부 판별
//        registry.addInterceptor(new FindAuthorizationInterceptor(findService))
//                .addPathPatterns("/user/**")
//                .order(2);
    }

    public List<String> excludeUrl(String... strings){
        List<String> excludeUrls =
                new ArrayList<>(Arrays.asList(new String[]{"/css/**", "/*.ico","/user/api-docs", "/webjars/**", "/error"}));
        excludeUrls.addAll(Arrays.asList(strings));
        return excludeUrls;
    }

}
