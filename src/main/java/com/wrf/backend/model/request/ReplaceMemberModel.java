package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
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
}

