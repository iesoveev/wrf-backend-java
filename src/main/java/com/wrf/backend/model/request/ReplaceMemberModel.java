package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class ReplaceMemberModel {

    @ApiModelProperty(value = "Идентификатор пользователя", required = true)
    @JsonProperty(value = "targetUserId")
    private String targetUserId;

    @ApiModelProperty(value = "Идентификатор польщователя", required = true)
    @JsonProperty(value = "newUserId")
    private String newUserId;

    @ApiModelProperty(value = "shiftId", required = true)
    @JsonProperty(value = "shiftId")
    private String shiftId;

    public String getTargetUserId() {
        return targetUserId;
    }

    public String getNewUserId() {
        return newUserId;
    }

    public String getShiftId() {
        return shiftId;
    }
}

