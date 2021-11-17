package com.hm.insuranceservice.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceHandler {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Object... arguments){
        return messageSource.getMessage(code,arguments,null);
    }

    public String getMessage(String code, Locale locale, Object... arguments){
        return messageSource.getMessage(code,arguments,locale);
    }

}
