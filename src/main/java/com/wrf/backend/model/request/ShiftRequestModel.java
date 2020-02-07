package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ShiftRequestModel {

    @ApiModelProperty("Идентификаторы участников команды")
    @JsonProperty("memberIds")
    private List<String> memberIds;
}
