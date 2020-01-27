package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class GeneralIdModel {

    @ApiModelProperty(value = "Идентификатор", required = true)
    @JsonProperty(value = "id")
    private String id;

    public GeneralIdModel(String id) {
        this.id = id;
    }

    public GeneralIdModel() {
    }

    public String getId() {
        return id;
    }
}
