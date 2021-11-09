package com.hm.userservice.global;

import com.hm.userservice.global.exception.InvalidLoginDtoError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(InvalidLoginDtoError.class)
    public ResponseEntity invalidLoginDto(){
        return ResponseEntity.badRequest().body(ResponseDto.builder()
                .result("EXCEPTION")
                .message("로그인 아이디 또는 비밀번호가 일치하지 않습니다.")
                .build()
        );
    }



}
