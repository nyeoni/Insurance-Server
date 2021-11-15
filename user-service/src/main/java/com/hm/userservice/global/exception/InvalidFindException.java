package com.hm.userservice.global.exception;

public class InvalidFindException extends RuntimeException {

    public static class InvalidLoginDtoException extends InvalidFindException{
    }

    public static class InvalidFindByIdException extends InvalidFindException{
    }

}
