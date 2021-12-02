package com.hm.userservice.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class EntityBody<T> {

    private HttpStatus status;
    private Boolean success;
    private String message;
    private T data;
    private List<ErrorDto> errors;

    public static <T> EntityBody<T> ok(T data, String message){
        EntityBody<T> entityBody = new EntityBody<T>();
        entityBody.setStatus(HttpStatus.OK);
        entityBody.setSuccess(true);
        entityBody.setData(data);
        entityBody.setMessage(message);
        return entityBody;
    }

    public static EntityBody ok(String message){
        EntityBody entityBody = new EntityBody();
        entityBody.setStatus(HttpStatus.OK);
        entityBody.setSuccess(true);
        entityBody.setMessage(message);
        return entityBody;
    }

    public static EntityBody error(){
        EntityBody entityBody = new EntityBody();
        entityBody.setSuccess(false);
        return entityBody;
    }

    public static EntityBody badRequest(String message, List<ErrorDto> errorDtoList){
        EntityBody entityBody = error();
        entityBody.setStatus(HttpStatus.BAD_REQUEST);
        entityBody.setMessage(message);
        entityBody.setErrors(errorDtoList);
        return entityBody;
    }

    public static EntityBody badRequest(String message){
        EntityBody entityBody = error();
        entityBody.setStatus(HttpStatus.BAD_REQUEST);
        entityBody.setMessage(message);
        return entityBody;
    }

    public static EntityBody serverError(String message, List<ErrorDto> errorDtoList){
        EntityBody entityBody = error();
        entityBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        entityBody.setMessage(message);
        entityBody.setErrors(errorDtoList);
        return entityBody;
    }

    public static EntityBody serverError(String message){
        EntityBody entityBody = error();
        entityBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        entityBody.setMessage(message);
        return entityBody;
    }

}
