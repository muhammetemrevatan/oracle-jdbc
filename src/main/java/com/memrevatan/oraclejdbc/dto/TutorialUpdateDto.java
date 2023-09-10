package com.memrevatan.oraclejdbc.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class TutorialCreateDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String title;
    private String description;
    private String content;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
