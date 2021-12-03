package com.hm.userservice.controller;

import com.hm.userservice.controller.dto.JoinDto;
import com.hm.userservice.controller.dto.LoginDto;
import com.hm.userservice.controller.dto.LoginResponseDto;
import com.hm.userservice.controller.dto.DetailUserDto;
import com.hm.userservice.domain.User;
import com.hm.userservice.global.EntityBody;
import com.hm.userservice.global.ErrorDto;
import com.hm.userservice.global.MessageSourceHandler;
import com.hm.userservice.service.join.JoinService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JoinController {

    private final JoinService joinService;
    private final MessageSourceHandler ms;
    private final Environment env;

    @PostMapping("/join")
    public ResponseEntity join(@Validated @RequestBody JoinDto joinDto, BindingResult bindingResult){
        if (bindResultHasErrors(bindingResult)) {
            String message = ms.getMessage("Error.Join.user");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult, ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        User joinUser = joinService.join(joinDto);
        String message = ms.getMessage("Join.user",joinDto.getLoginId());
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(DetailUserDto.byUser(joinUser),message));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Validated @RequestBody LoginDto loginDto, BindingResult bindingResult, HttpServletResponse response){
        if (bindResultHasErrors(bindingResult)) {
            String message = ms.getMessage("Error.Login.loginDto");
            List<ErrorDto> errorDtoList = ErrorDto.byBindingResult(bindingResult,ms);
            log.error(message);
            return ResponseEntity.badRequest().body(EntityBody.badRequest(message,errorDtoList));
        }
        User user = joinService.login(loginDto);
        response.addHeader("token",makeToken(user.getLoginId()));
        String message = ms.getMessage("Login.loginDto",user.getLoginId());
        log.info(message);
        return ResponseEntity.ok().body(EntityBody.ok(new LoginResponseDto(user.getLoginId(),user.getName()),message));
    }

    private boolean bindResultHasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(String.valueOf(bindingResult.getAllErrors()));
            return true;
        }
        return false;
    }

    private String makeToken(String loginId){
        String token = Jwts.builder()
                .setSubject(loginId)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512,env.getProperty("token.secret"))
                .compact();
        return token;
    }

}
