package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ChecklistPackItemStatusModel {

    @NotEmpty
    @ApiModelProperty(value = "items", required = true)
    private List<ChecklistItemStatusModel> items;

}
