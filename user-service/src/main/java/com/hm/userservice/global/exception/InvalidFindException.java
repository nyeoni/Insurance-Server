package com.hm.userservice.global.exception;

public class InvalidFindException extends RuntimeException {

    public static class ByLoginDto extends InvalidFindException{
    }

    public static class ById extends InvalidFindException{
    }

}
