package com.memrevatan.oraclejdbc.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorDto {
    private LocalDateTime timestamp;
    private String status;
    private List<String> errors;
}
