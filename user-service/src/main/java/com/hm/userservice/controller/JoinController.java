package com.hm.userservice.controller;

import com.hm.userservice.controller.constants.LoginConst;
import com.hm.userservice.controller.dto.JoinDto;
import com.hm.userservice.controller.dto.LoginDto;
import com.hm.userservice.controller.dto.LoginResponseDto;
import com.hm.userservice.controller.dto.DetailUserDto;
import com.hm.userservice.domain.User;
import com.hm.userservice.global.EntityBody;
import com.hm.userservice.global.ErrorDto;
import com.hm.userservice.global.MessageSourceHandler;
import com.hm.userservice.service.join.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JoinController {

    private final JoinService joinService;
    private final MessageSourceHandler ms;

    @PostMapping("/join")
    public ResponseEntity join(@Validated @RequestBody JoinDto joinDto, BindingResult bindingResult){
        if (bindResultHasErrors(bindingResult)) {
            String message = ms.getMessage("Error.Join.user");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult, ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        User joinUser = joinService.join(joinDto);
        String message = ms.getMessage("Join.user");
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailUserDto.byUser(joinUser),message));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Validated @RequestBody LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request){
        if (bindResultHasErrors(bindingResult)) {
            String message = ms.getMessage("Error.Login.loginDto");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult,ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        User user = joinService.login(loginDto);
        String loginId = setLoginSession(user, request);
        String message = ms.getMessage("Login.loginDto",loginId);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(new LoginResponseDto(user.getLoginId(),user.getName()),message));
    }


    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        String logoutUser = (String) session.getAttribute(LoginConst.LOGIN_USER);
        session.removeAttribute(LoginConst.LOGIN_USER);
        String message = ms.getMessage("Logout.user",logoutUser);
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(message));
    }

    private boolean bindResultHasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getAllErrors()));
            return true;
        }
        return false;
    }

    private String setLoginSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(LoginConst.LOGIN_USER,user.getLoginId());
        String loginId = session.getAttribute(LoginConst.LOGIN_USER).toString();
        log.info("SESSION_ID = [{}] LOGIN_USER = [{}]",session.getId(), loginId);
        return loginId;
    }

}
