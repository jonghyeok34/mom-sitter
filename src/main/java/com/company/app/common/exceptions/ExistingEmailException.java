package com.company.app.common.exceptions;

public class ExistingEmailException extends RuntimeException {
    public ExistingEmailException(){
        
    }
    public ExistingEmailException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public ExistingEmailException(String errorMessage) {
        super(errorMessage);
    }
}
