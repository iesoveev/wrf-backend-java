package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class EventModel {

    @ApiModelProperty(value = "Идентификатор смены", required = true)
    @JsonProperty(value = "shiftId")
    private String shiftId;

    @ApiModelProperty(value = "Текст события", required = true)
    @JsonProperty(value = "text")
    private String text;

    public String getShiftId() {
        return shiftId;
    }

    public String getText() {
        return text;
    }
}
