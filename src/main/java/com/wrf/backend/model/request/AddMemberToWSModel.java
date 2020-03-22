package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public final class AddMemberToWSModel {

    @NotBlank
    @ApiModelProperty(value = "userId", required = true)
    private final String userId;

    @NotBlank
    @ApiModelProperty(value = "shiftId", required = true)
    private final String shiftId;
}
