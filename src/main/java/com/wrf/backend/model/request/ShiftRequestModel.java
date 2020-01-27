package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ShiftRequestModel {

    @ApiModelProperty("Идентификаторы участников команды")
    @JsonProperty("memberIds")
    private List<String> memberIds;

    public List<String> getMemberIds() {
        return memberIds;
    }
}
