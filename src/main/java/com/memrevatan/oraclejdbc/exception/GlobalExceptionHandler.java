package com.memrevatan.oraclejdbc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(DataAccessException e) {
        log.error(ERROR_PATTERN, "Database transaction is failure for Tutorial.", e.getMessage());
        return ResponseEntity.internalServerError().body("Database transaction is failure for Tutorial.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();

        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }
}
