package com.hm.userservice.global;

import com.hm.userservice.global.exception.AlreadyExistUser;
import com.hm.userservice.global.exception.InvalidFindException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionControllerConfig {

    @Autowired
    MessageSourceHandler ms;

    @ExceptionHandler(InvalidFindException.ByLoginDto.class)
    public ResponseEntity controller(InvalidFindException exception){
        String message = ms.getMessage("Error.Invalid.loginDto");
        log.error(message);
        return ResponseEntity.internalServerError().body(EntityBody.serverError(message));
    }

    @ExceptionHandler(InvalidFindException.ById.class)
    public ResponseEntity controller(InvalidFindException.ById exception){
        String message = ms.getMessage("Error.Invalid.user.id");
        log.error(message);
        return ResponseEntity.internalServerError().body(EntityBody.serverError(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity unique(ConstraintViolationException exception){
        String message = ms.getMessage("Error.Unique.user.loginId");
        log.error(message);
        return ResponseEntity.internalServerError().body(EntityBody.serverError(message));
    }

    @ExceptionHandler(AlreadyExistUser.class)
    public ResponseEntity alreadyExistUser(AlreadyExistUser exception){
        String message = ms.getMessage("Error.Unique.user.loginId");
        log.error(message);
        return ResponseEntity.internalServerError().body(EntityBody.serverError(message));
    }

}
