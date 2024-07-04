package com.inditex.hiring.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParseDatesException extends RuntimeException {

    public ParseDatesException(String msg) {super(msg);}
}
