package com.memrevatan.oraclejdbc.entity.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column("ID")
    protected Long id;
}
