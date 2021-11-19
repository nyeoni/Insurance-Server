package com.hm.insuranceservice.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("PREHANDLE: [REQUEST_URI:{} HANDLER: {}]",request.getRequestURI(),handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("STATUS: [{}]",response.getStatus());
        if(ex!=null){
            log.info("ERROR: [{}]",ex.getMessage());
        }
        if(response.getStatus() == 200)
            log.info("SUCCESS: {} HANDLER: {}",response.getStatus(),handler);
    }
}
