package com.company.app.common.exceptions;

public class AlreadyAcceptedException  extends RuntimeException {
    public AlreadyAcceptedException(){
        
    }
    public AlreadyAcceptedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public AlreadyAcceptedException(String errorMessage) {
        super(errorMessage);
    }
}
