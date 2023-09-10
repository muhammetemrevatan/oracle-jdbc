package com.memrevatan.oraclejdbc.exception;

public class TutorialNotDeletedException extends RuntimeException {
    public TutorialNotDeletedException(String message) {
        super(message);
    }

    public TutorialNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
