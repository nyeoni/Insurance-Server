package com.hm.contractservice.global;

import com.hm.contractservice.global.exception.ClientException;
import com.hm.contractservice.global.exception.InvalidFindException;
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

    @ExceptionHandler(InvalidFindException.ById.class)
    public ResponseEntity invalidFindById(InvalidFindException.ById exception){
        String message = ms.getMessage("Error.Invalid.contract.id","계약");
        log.error(message);
        return ResponseEntity.badRequest().body(EntityBody.serverError(message));
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity findInsurance(ClientException exception){
        String message = ms.getMessage("Error.Find",exception.getClass());
        return ResponseEntity.badRequest().body(EntityBody.serverError(message));
    }

}
