package com.hm.insuranceservice.global;

import com.hm.insuranceservice.global.exception.InvalidFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerConfig {

    @Autowired
    MessageSourceHandler ms;

    @ExceptionHandler(InvalidFindException.ById.class)
    public ResponseDto invalidFindById(InvalidFindException.ById exception){
        return ResponseDto.builder().message(ms.getMessage("Invalid")).build();
    }




}
