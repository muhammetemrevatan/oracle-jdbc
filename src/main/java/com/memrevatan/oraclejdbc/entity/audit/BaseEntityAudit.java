package com.memrevatan.oraclejdbc.entity.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntityAudit extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column("CREATED_AT")
    protected LocalDateTime createdAt;
    @Column("UPDATED_AT")
    protected LocalDateTime updatedAt;
    @Column("CREATED_BY")
    protected String createdBy;
    @Column("UPDATED_BY")
    protected String updatedBy;
}
