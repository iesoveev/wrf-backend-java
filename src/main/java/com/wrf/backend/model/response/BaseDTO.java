package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseDTO {

    private String id;

    private String create_at;

    public String getId() {
        return id;
    }
}
