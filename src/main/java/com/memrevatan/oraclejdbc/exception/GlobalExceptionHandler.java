package com.memrevatan.oraclejdbc.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERROR_PATTERN = "[({})]  [ErrorMessage] --> {}";

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorDto> handleDataAccessException(DataAccessException e) {
        log.error(ERROR_PATTERN, "Error occurred while accessing data.", e.getMessage());
        List<String> errors = List.of("Error occurred while accessing data.");
        ErrorDto errorDto = buildErrorResponse(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDto> handleEmptyResultDataAccessException(DataAccessException e) {
        log.error(ERROR_PATTERN, "ID is not found.", e.getMessage());
        List<String> errors = List.of("ID is not found.");
        ErrorDto errorDto = buildErrorResponse(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TutorialNotSavedException.class)
    public ResponseEntity<ErrorDto> handleTutorialNotSavedException(TutorialNotSavedException e) {
        log.error(ERROR_PATTERN, "Tutorial not saved.", e.getMessage());
        List<String> errors = List.of("Tutorial not saved.");
        ErrorDto errorDto = buildErrorResponse(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();

        ErrorDto errorResponse = buildErrorResponse(errors, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        List<String> errorsList = new ArrayList<>();

        if (cause instanceof InvalidFormatException invalidFormatException) {
            String fieldName = invalidFormatException.getPath().isEmpty() ? "" : invalidFormatException.getPath().get(0).getFieldName();
            String value = invalidFormatException.getValue() == null ? "null" : invalidFormatException.getValue().toString();

            Class<?> targetType = invalidFormatException.getTargetType();
            String validValues = "";
            if (targetType.isEnum()) {
                validValues = " Valid values are: " + Arrays.toString(targetType.getEnumConstants());
            }

            String message = String.format("Invalid value '%s' for field '%s'.%s", value, fieldName, validValues);
            errorsList.add(message);
        }

        ErrorDto errorResponse = buildErrorResponse(errorsList, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ErrorDto errorResponse = buildErrorResponse(errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorDto buildErrorResponse(List<String> errors, HttpStatus status) {
        ErrorDto errorResponse = new ErrorDto();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(status.name());
        errorResponse.setErrors(errors);
        return errorResponse;
    }

}
