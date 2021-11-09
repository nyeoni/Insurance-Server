package com.hm.userservice.global.Interceptor;

import com.hm.userservice.controller.constants.LoginConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class SessionAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("인증 필터링 시작");
        HttpSession session = request.getSession(false);
        if(session==null||session.getAttribute(LoginConst.LOGIN_USER)==null){
            log.info("미인증 사용자 필터링");
            return false;
        }
        session.setMaxInactiveInterval(60);
        log.info("인증된 사용자");
        return true;
    }

}
