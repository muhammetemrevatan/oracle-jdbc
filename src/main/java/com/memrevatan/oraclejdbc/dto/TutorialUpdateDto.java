package com.memrevatan.oraclejdbc.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TutorialUpdateDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String title;
    private String description;
    private String content;
    private String status;
}
