package com.afkl.travel.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TravelControllerExceptionAdvice {

    @ExceptionHandler(value = {LocationNotFoundException.class,TranslationNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionError handleResourceNotFound(RuntimeException ex) {
        ExceptionError error = ExceptionError.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
        return error;
    }
}
