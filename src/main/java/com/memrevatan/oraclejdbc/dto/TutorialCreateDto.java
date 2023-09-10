package com.memrevatan.oraclejdbc.dto;

import com.memrevatan.oraclejdbc.enums.TutorialStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class TutorialCreateDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @Size(max = 255, message = "Description should not be greater than 255 characters")
    private String description;
    @NotBlank(message = "Content cannot be blank")
    private String content;
    private TutorialStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
