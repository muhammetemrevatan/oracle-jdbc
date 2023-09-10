package com.memrevatan.oraclejdbc.entity;

import com.memrevatan.oraclejdbc.entity.audit.BaseEntityAudit;
import com.memrevatan.oraclejdbc.enums.TutorialStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial extends BaseEntityAudit implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column("TITLE")
    private String title;
    @Column("DESCRIPTION")
    private String description;
    @Column("CONTENT")
    private String content;
    @Column("STATUS")
    private TutorialStatus status;
}
