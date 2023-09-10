package com.memrevatan.oraclejdbc.entity;

import com.memrevatan.oraclejdbc.entity.audit.BaseEntityAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial extends BaseEntityAudit implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String title;
    private String description;
    private String content;
    private String status;
}
