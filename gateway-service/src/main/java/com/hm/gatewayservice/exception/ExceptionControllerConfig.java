package com.hm.gatewayservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hm.gatewayservice.exception.InvalidAuthException.*;

@RestControllerAdvice
public class ExceptionControllerConfig {

    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity invalidToken(InvalidToken exception){
        exception.getMessage();
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(RequireAuth.class)
    public ResponseEntity requireAuth(RequireAuth exception){
        exception.getMessage();
        return ResponseEntity.badRequest().build();
    }

}
