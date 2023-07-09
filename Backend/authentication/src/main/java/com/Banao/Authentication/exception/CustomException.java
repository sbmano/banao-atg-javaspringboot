package com.Banao.Authentication.exception;

public class CustomException extends RuntimeException{
    private static final long serviceVersionUID = 1L;

    public CustomException(String message){
        super(message);
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
