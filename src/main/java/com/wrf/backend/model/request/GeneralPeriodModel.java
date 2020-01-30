package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class GeneralPeriodModel {

    @ApiModelProperty(value = "Начало периода")
    @JsonProperty(value = "beginDate")
    private String beginDate;

    @ApiModelProperty(value = "Конец периода")
    @JsonProperty(value = "endDate")
    private String endDate;

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
