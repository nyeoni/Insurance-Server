package com.hm.userservice.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@Getter
public class ResponseDto extends ResponseEntity {

    private HttpStatus status;
    private Boolean success;
    private String message;
    private Object data;
    private List<ErrorDto> errors;

    public ResponseDto(ResponseWrapperDto responseWrapper, HttpStatus status){
        super(responseWrapper,status);
    }

    public static ResponseDto makeResponseDto(builder builder){
        ResponseWrapperDto wrapperDto = ResponseWrapperDto.builder()
                .status(builder.getStatus())
                .success(builder.getSuccess())
                .message(builder.getMessage())
                .data(builder.getData())
                .errors(builder.getErrors()).build();
        return new ResponseDto(wrapperDto,builder.getStatus());
    }

    public static builder builder(){
        return new builder();
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @Getter @Setter
    public static class ResponseWrapperDto{
        private HttpStatus status;
        private Boolean success;
        private String message;
        private Object data;
        private List<ErrorDto> errors;
    }


    @Getter
    public static class builder {
        private HttpStatus status;
        private Boolean success;
        private String message;
        private Object data;
        private List<ErrorDto> errors;

        public builder status(HttpStatus httpStatus){
            this.status = httpStatus;
            return this;
        }


        public builder success(Boolean bool){
            this.success = bool;
            return this;
        }

        public builder ok(){
            this.success = true;
            this.status = HttpStatus.OK;
            return this;
        }

        public builder fail(HttpStatus httpStatus){
            this.success = false;
            this.status = httpStatus;
            return this;
        }
        public builder badRequest(){
            this.success = false;
            this.status = HttpStatus.BAD_REQUEST;
            return this;
        }

        public builder message(String message){
            this.message = message;
            return this;
        }

        public builder data(Object data){
            this.data = data;
            return this;
        }

        public builder errors(ErrorDto... errorDtoList){
            this.errors = Arrays.asList(errorDtoList);
            return this;
        }
        public builder errors(List<ErrorDto> errorDtoList){
            this.errors = errorDtoList;
            return this;
        }
        public ResponseDto build(){
            return makeResponseDto(this);
        }

    }

}
