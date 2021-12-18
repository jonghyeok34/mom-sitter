package com.company.app.common.exceptions;

public class FormValueRequiredExcpetion extends RuntimeException {
    public FormValueRequiredExcpetion(){
        
    }
    public FormValueRequiredExcpetion(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public FormValueRequiredExcpetion(String errorMessage) {
        super(errorMessage);
    }
}
