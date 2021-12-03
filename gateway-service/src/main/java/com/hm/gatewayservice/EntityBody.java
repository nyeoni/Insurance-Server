package com.hm.gatewayservice;

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

    private static EntityBody badRequest(String message){
        EntityBody entityBody = new EntityBody();
        entityBody.setStatus(HttpStatus.BAD_REQUEST);
        entityBody.setSuccess(false);
        entityBody.setMessage(message);
        return entityBody;
    }
}
