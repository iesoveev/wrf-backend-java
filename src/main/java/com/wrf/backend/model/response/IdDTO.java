package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class IdDTO {

    private Long id;
}
