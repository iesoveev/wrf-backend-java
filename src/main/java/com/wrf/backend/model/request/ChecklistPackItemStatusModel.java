package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ChecklistPackItemStatusModel {

    @ApiModelProperty(value = "items", required = true)
    private List<ChecklistItemStatusModel> items;

}
