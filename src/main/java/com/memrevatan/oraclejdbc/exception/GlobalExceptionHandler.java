package com.memrevatan.oraclejdbc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERROR_PATTERN = "[({})]  [ErrorMessage] --> {}";

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        log.error(ERROR_PATTERN, "Error occurred while accessing data.", e.getMessage());
        return ResponseEntity.internalServerError().body("Database error occurred: " + e.getMessage());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResultDataAccessException(DataAccessException e) {
        log.error(ERROR_PATTERN, "ID is not find.", e.getMessage());
        return ResponseEntity.internalServerError().body("ID is not find.");
    }

    @ExceptionHandler(TutorialNotSavedException.class)
    public ResponseEntity<String> handleTutorialNotSavedException(DataAccessException e) {
        log.error(ERROR_PATTERN, "Tutorial not saved.", e.getMessage());
        return ResponseEntity.internalServerError().body("Tutorial not saved.");
    }
}
