package com.memrevatan.oraclejdbc.exception;

public class TutorialNotUpdateException extends RuntimeException {
    public TutorialNotUpdateException(String message) {
        super(message);
    }

    public TutorialNotUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
