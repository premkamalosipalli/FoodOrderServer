package com.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserDefinedException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserDefinedException(String message){
        super(message);
    }
}
