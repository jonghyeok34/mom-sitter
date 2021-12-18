package com.company.app.common.exceptions;

public class ExistingUserException extends RuntimeException {
    public ExistingUserException(){
        
    }
    public ExistingUserException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public ExistingUserException(String errorMessage) {
        super(errorMessage);
    }
}
