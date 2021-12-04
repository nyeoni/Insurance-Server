package com.hm.contractservice.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ErrorDto {

    private String objectName;
    private String field;
    private String code;
    private String message;
    private List<ErrorDto> fields;

    public ErrorDto(String objectName, String field, String code, String message) {
        this.objectName = objectName;
        this.field = field;
        this.code = code;
        this.message = message;
    }

    public ErrorDto(String objectName, String code, String message) {
        this.objectName = objectName;
        this.code = code;
        this.message = message;
    }

    public ErrorDto(List<ErrorDto> fieldErrorList) {
        this.fields = fieldErrorList;
    }


    public static List<ErrorDto> byBindingResult(BindingResult bindingResult, MessageSourceHandler ms){
        List<ErrorDto> errorList = new ArrayList<ErrorDto>();
        if(bindingResult.hasErrors()){
            bindingResult.getGlobalErrors().stream()
                    .forEach(o -> errorList.add(new ErrorDto(o.getObjectName(), o.getCodes()[0],
                            ms.getMessage(o.getCodes()[0],o.getArguments()))));
            List<ErrorDto> fieldErrorList = new ArrayList<ErrorDto>();
            bindingResult.getFieldErrors().stream()
                    .forEach(f -> fieldErrorList.add(new ErrorDto(f.getObjectName(), f.getField(),f.getCodes()[0],
                            ms.getMessage(f.getCodes()[0],f.getArguments()))));
            errorList.add(new ErrorDto(fieldErrorList));
        }
        return errorList;
    }

}
