package com.hm.userservice.global.Interceptor;

import com.hm.userservice.controller.constants.LoginConst;
import com.hm.userservice.service.find.FindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class FindAuthorizationInterceptor implements HandlerInterceptor {

    private final FindService findService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("조회 권한 체크 시작");
        String loginId = request.getSession(false).getAttribute(LoginConst.LOGIN_USER).toString();

        if(findService.CheckFindAuthority(loginId)) {
            log.info("조회 권한 체크 완료");
            long endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            return true;
        }
        log.info("조회 권한 없음");
        return false;
    }

}
