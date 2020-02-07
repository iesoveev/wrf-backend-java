package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralIdModel {

    @ApiModelProperty(value = "Идентификатор", required = true)
    @JsonProperty(value = "id")
    private String id;
}
