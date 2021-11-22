package com.hm.userservice.global;

import com.hm.userservice.global.exception.InvalidFindException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class ExceptionControllerConfig {

    @Autowired
    MessageSourceHandler ms;

    @ExceptionHandler(InvalidFindException.ByLoginDto.class)
    public ResponseDto controller(InvalidFindException exception){
        return new ResponseDto.builder()
                .badRequest()
                .message(ms.getMessage("invalid.LoginDto"))
                .build();
    }

    @ExceptionHandler(InvalidFindException.ById.class)
    public ResponseDto controller(InvalidFindException.ById exception){
        return new ResponseDto.builder()
                .badRequest()
                .message(ms.getMessage("invalid.User.Id"))
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseDto unique(ConstraintViolationException exception){
        return new ResponseDto.builder()
                .badRequest()
                .message(ms.getMessage("unique.User.Id"))
                .build();
    }

}
