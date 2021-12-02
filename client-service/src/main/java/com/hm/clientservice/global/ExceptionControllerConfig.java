package com.hm.clientservice.global;

import com.hm.clientservice.global.Exception.InvalidFindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionControllerConfig {

    @Autowired
    MessageSourceHandler ms;

    @ExceptionHandler(InvalidFindException.byId.class)
    public ResponseEntity invalidFindById(InvalidFindException.byId exception){
        String message = ms.getMessage("Error.Invalid.client.id");
        log.error(message);
        return ResponseEntity.badRequest().body(EntityBody.badRequest(message));
    }



}
