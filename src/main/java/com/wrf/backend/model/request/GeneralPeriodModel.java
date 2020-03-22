package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class GeneralPeriodModel {

    @ApiModelProperty(value = "Начало периода")
    private final String beginDate;

    @ApiModelProperty(value = "Конец периода")
    private final String endDate;
}
