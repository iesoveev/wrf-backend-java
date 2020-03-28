package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralPeriodModel {

    @ApiModelProperty(value = "Начало периода")
    private String beginDate;

    @ApiModelProperty(value = "Конец периода")
    private String endDate;
}
