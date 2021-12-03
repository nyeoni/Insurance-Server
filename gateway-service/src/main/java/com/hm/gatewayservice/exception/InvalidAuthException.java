package com.hm.gatewayservice.exception;

public class InvalidAuthException extends RuntimeException{

    public InvalidAuthException(String message) {
        super(message);
    }

    public static class InvalidToken extends InvalidAuthException{

        public InvalidToken() {
            super("유효하지 않은 토큰입니다.");
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    public static class RequireAuth extends InvalidAuthException{

        public RequireAuth() {
            super("인증이 필요합니다.");
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
