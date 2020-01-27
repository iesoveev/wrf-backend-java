package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class AddMemberToWSModel {

    @ApiModelProperty(value = "userId", required = true)
    @JsonProperty(value = "userId")
    private String userId;

    @ApiModelProperty(value = "shiftId", required = true)
    @JsonProperty(value = "shiftId")
    private String shiftId;

    public String getUserId() {
        return userId;
    }

    public String getShiftId() {
        return shiftId;
    }
}
