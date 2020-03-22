package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public final class EventModel {

    @NotBlank
    @ApiModelProperty(value = "Идентификатор смены", required = true)
    private final String shiftId;

    @NotBlank
    @ApiModelProperty(value = "Текст события", required = true)
    private final String text;
}
