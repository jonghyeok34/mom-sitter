package com.company.app.common.exceptions;

public class NotValidChildException extends RuntimeException {
    public NotValidChildException(){
        
    }
    public NotValidChildException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public NotValidChildException(String errorMessage) {
        super(errorMessage);
    }
}
