package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public final class ReplaceMemberModel {

    @NotBlank
    @ApiModelProperty(value = "Идентификатор пользователя", required = true)
    private final String targetUserId;

    @NotBlank
    @ApiModelProperty(value = "Идентификатор польщователя", required = true)
    private final String newUserId;

    @NotBlank
    @ApiModelProperty(value = "shiftId", required = true)
    private final String shiftId;
}

