package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class UpdateModel {

    @ApiModelProperty(value = "id", required = true)
    private String id;
}
