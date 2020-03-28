package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseDTO {

    private Long id;

    private String create_at;

    public Long getId() {
        return id;
    }
}
