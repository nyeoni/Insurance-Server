package com.hm.insuranceservice.global;

import com.hm.insuranceservice.global.exception.InvalidFindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerConfig {

    @Autowired
    MessageSourceHandler ms;

    @ExceptionHandler(InvalidFindException.ById.class)
    public ResponseEntity invalidFindById(InvalidFindException.ById exception){
        String message = ms.getMessage("Error.Invalid.insurance.id");
        log.error(message);
        return ResponseEntity.badRequest().body(EntityBody.badRequest(message));
    }




}
