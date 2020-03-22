package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class AndroidLogModel {

    @ApiModelProperty(value = "Текст ошибки", required = true)
    private final String message;
}
