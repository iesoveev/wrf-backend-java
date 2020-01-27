package com.wrf.backend.model.response;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseDTO {

    private String id;

    public String getId() {
        return id;
    }
}
