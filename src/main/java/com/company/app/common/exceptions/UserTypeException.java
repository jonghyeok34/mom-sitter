package com.company.app.common.exceptions;

public class UserTypeException extends RuntimeException {
    public UserTypeException(){
        
    }
    public UserTypeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public UserTypeException(String errorMessage) {
        super(errorMessage);
    }
}
