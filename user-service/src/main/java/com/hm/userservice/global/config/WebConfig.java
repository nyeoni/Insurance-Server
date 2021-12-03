package com.hm.userservice.global.config;

import com.hm.userservice.global.Interceptor.LogInterceptor;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //로그
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrl("/csrf"));
    }

    public List<String> excludeUrl(String... strings){
        List<String> excludeUrls =
                new ArrayList<>(Arrays.asList(new String[]{"/css/**", "/*.ico","/user/api-docs", "/webjars/**", "/error"}));
        excludeUrls.addAll(Arrays.asList(strings));
        return excludeUrls;
    }

}
