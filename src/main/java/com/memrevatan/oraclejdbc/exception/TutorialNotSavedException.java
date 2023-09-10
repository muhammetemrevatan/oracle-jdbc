package com.memrevatan.oraclejdbc.exception;

public class TutorialNotSavedException extends RuntimeException {
    public TutorialNotSavedException(String message) {
        super(message);
    }

    public TutorialNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
