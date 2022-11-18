package com.afkl.travel.exercise.exception;

public class TranslationNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TranslationNotFoundException(String message) {
        super(message);
    }
}
