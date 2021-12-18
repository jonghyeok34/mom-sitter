package com.company.app.common.exceptions;

public class UserTokenException extends RuntimeException {
    public UserTokenException(){
        
    }
    public UserTokenException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public UserTokenException(String errorMessage) {
        super(errorMessage);
    }
}
