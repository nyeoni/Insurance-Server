package com.hm.clientservice.global;

import com.hm.clientservice.global.Exception.InvalidFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerConfig {

    @Autowired
    MessageSourceHandler ms;

    @ExceptionHandler(InvalidFindException.byId.class)
    public ResponseDto invalidFindById(InvalidFindException.byId exception){
        return ResponseDto.builder().badRequest().message(ms.getMessage("Invalid.client.id")).build();
    }



}
