package com.hm.userservice.global;

import com.hm.userservice.global.exception.InvalidFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class ExceptionControllerConfig {

    @Autowired
    MessageSourceHandler ms;


    @ExceptionHandler(InvalidFindException.InvalidLoginDtoException.class)
    public ResponseDto controller(InvalidFindException exception){
        return new ResponseDto.builder()
                .badRequest()
                .message(ms.getMessage("invalid.LoginDto"))
                .build();
    }

    @ExceptionHandler(InvalidFindException.InvalidFindByIdException.class)
    public ResponseDto controller(InvalidFindException.InvalidFindByIdException exception){
        return new ResponseDto.builder()
                .badRequest()
                .message(ms.getMessage("invalid.User.Id"))
                .build();
    }


}
