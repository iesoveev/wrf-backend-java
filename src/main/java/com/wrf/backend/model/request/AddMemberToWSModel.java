package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AddMemberToWSModel {

    @ApiModelProperty(value = "userId", required = true)
    @JsonProperty(value = "userId")
    private String userId;

    @ApiModelProperty(value = "shiftId", required = true)
    @JsonProperty(value = "shiftId")
    private String shiftId;
}
