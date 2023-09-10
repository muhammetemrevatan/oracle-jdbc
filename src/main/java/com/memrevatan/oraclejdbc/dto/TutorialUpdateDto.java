package com.memrevatan.oraclejdbc.dto;

import com.memrevatan.oraclejdbc.enums.TutorialStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TutorialUpdateDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Content is mandatory")
    private String content;
    private TutorialStatus status;
}
