package com.hm.insuranceservice.global;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<E>  {

    public String result;

    public String message;

    public E data;

}
