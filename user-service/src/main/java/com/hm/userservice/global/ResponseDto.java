package com.hm.userservice.global;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

    private String result;
    private String message;
    private Object data;

}
