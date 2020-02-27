package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ChecklistStatusModel {

    @ApiModelProperty(value = "Статус таски чек-листа", required = true)
    private String status;
}
