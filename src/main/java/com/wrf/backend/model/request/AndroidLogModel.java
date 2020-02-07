package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AndroidLogModel {

    @ApiModelProperty(value = "Текст ошибки", required = true)
    @JsonProperty(value = "message")
    private String message;
}
