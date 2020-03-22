package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public final class ChecklistStatusModel {

    @NotBlank
    @ApiModelProperty(value = "Статус таски чек-листа", required = true)
    private final String status;
}
