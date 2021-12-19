package com.company.app;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import com.company.app.common.exceptions.AlreadyAcceptedException;
import com.company.app.common.exceptions.ExistingUserException;
import com.company.app.common.exceptions.FormValueRequiredExcpetion;
import com.company.app.common.exceptions.NotValidChildException;
import com.company.app.common.exceptions.UserAuthException;
import com.company.app.common.exceptions.UserTokenException;
import com.company.app.common.exceptions.UserTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected Map<String, String> handleConflict(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ObjectError firstErr = ex.getBindingResult().getAllErrors().get(0);
        errors.put("message", firstErr.getDefaultMessage());

        return errors;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = { ExistingUserException.class })
    protected Map<String, String> handleConflict(ExistingUserException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());

        return errors;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { UserAuthException.class })
    protected Map<String, String> handleConflict(UserAuthException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = { UserTokenException.class })
    protected Map<String, String> handleConflict(UserTokenException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = { UserTypeException.class })
    protected Map<String, String> handleConflict(UserTypeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = { AlreadyAcceptedException.class })
    protected Map<String, String> handleConflict(AlreadyAcceptedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { FormValueRequiredExcpetion.class })
    protected Map<String, String> handleConflict(FormValueRequiredExcpetion ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { DateTimeParseException.class })
    protected Map<String, String> handleConflict(DateTimeParseException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "날짜는 yyyyMMdd의 형태로 입력해야 합니다");
        return errors;
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = { NotValidChildException.class })
    protected Map<String, String> handleConflict(NotValidChildException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }
}
