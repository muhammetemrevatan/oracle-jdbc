package com.memrevatan.oraclejdbc.exception;

public class TutorialNotUpdatedException extends RuntimeException {
    public TutorialNotUpdatedException(String message) {
        super(message);
    }

    public TutorialNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
