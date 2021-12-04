package com.hm.contractservice.global.exception;

public class ClientException extends RuntimeException{

    public static class FindClient extends ClientException{
        public FindClient() {
            super("Client 조회에 실패했습니다.");
        }
    }
    public static class FindEmployee extends ClientException{
        public FindEmployee() {
            super("Employee 조회에 실패했습니다.");
        }
    }
    public static class FindInsurance extends ClientException{
        public FindInsurance() {
            super("Insurance 조회에 실패했습니다.");
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
