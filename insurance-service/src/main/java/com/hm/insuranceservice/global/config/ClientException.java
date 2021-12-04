package com.hm.insuranceservice.global.config;

public class ClientException extends RuntimeException{

    public static class findContract extends ClientException {
        public findContract() {
            super("Contract 조회에 실패했습니다.");
        }
    }

    public ClientException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
