package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ChecklistItemStatusModel {

    @ApiModelProperty(value = "Идентификатор таски чек-листа", required = true)
    private String itemId;

    @ApiModelProperty(value = "Статус", required = true)
    private String status;
}
