package com.memrevatan.oraclejdbc.entity.audit;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntityAudit extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected String createdBy;
    protected String updatedBy;
}
