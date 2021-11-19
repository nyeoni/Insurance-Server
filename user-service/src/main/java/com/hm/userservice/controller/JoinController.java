package com.hm.userservice.controller;

import com.hm.userservice.controller.constants.LoginConst;
import com.hm.userservice.controller.dto.JoinDto;
import com.hm.userservice.controller.dto.LoginDto;
import com.hm.userservice.controller.dto.UserDetailDto;
import com.hm.userservice.domain.User;
import com.hm.userservice.global.ErrorDto;
import com.hm.userservice.global.MessageSourceHandler;
import com.hm.userservice.global.ResponseDto;
import com.hm.userservice.service.join.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping("user-service")
@RequiredArgsConstructor
@RestController
public class JoinController {

    private final JoinService joinService;
    private final MessageSourceHandler ms;

    @PostMapping("/join")
    public ResponseDto join(@Validated @RequestBody JoinDto joinDto, BindingResult bindingResult){
        if (bindResultHasErrors(bindingResult)) {
            return ResponseDto.builder().fail(HttpStatus.BAD_REQUEST)
                    .message(ms.getMessage("binding.JoinDto"))
                    .errors(ErrorDto.byBindingResult(bindingResult,ms))
                    .build();
        }
        User join = joinService.join(joinDto);
        return ResponseDto.builder().ok()
                .message(ms.getMessage("join.User",join.getUserId())).data(UserDetailDto.byUser(join)).build();
    }

    @PostMapping("/login")
    public ResponseDto login(@Validated @RequestBody LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request){
        if (bindResultHasErrors(bindingResult))
            return ResponseDto.builder().badRequest().errors(ErrorDto.byBindingResult(bindingResult,ms)).build();
        if(joinService.login(loginDto)){
            setLoginSession(loginDto,request);
        }
        return ResponseDto.builder().ok()
                .message(ms.getMessage("login.User",loginDto.getLoginId())).data(loginDto).build();
    }


    @RequestMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request){
        request.getSession().invalidate();
        return ResponseEntity.ok().build();
    }

    private boolean bindResultHasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getAllErrors()));
            return true;
        }
        return false;
    }

    private String setLoginSession(LoginDto loginDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        joinService.login(loginDto);
        session.setAttribute(LoginConst.LOGIN_USER,loginDto.getLoginId());
        session.setMaxInactiveInterval(60);
        String loginId = session.getAttribute(LoginConst.LOGIN_USER).toString();
        log.info("SESSION_ID = [{}] LOGIN_USER = [{}]",session.getId(), loginId);
        return loginId;
    }

}
