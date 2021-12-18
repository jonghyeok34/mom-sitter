package com.company.app.common.exceptions;

public class UserAuthException extends RuntimeException {
    public UserAuthException(){
        
    }
    public UserAuthException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public UserAuthException(String errorMessage) {
        super(errorMessage);
    }
}
