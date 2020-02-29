package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChecklistRequest {

    @ApiModelProperty(value = "Идетификатор роли", required = true)
    private String roleId;

    @ApiModelProperty(value = "Идетификатор категории", required = true)
    private String categoryId;

    @ApiModelProperty(value = "Название")
    private String title;

    @ApiModelProperty(value = "Подназвание")
    private String subtitle;

    @ApiModelProperty(value = "Описание")
    private String description;
}
