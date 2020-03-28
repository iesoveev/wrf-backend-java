package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public final class ReplaceMemberModel {

    @NotNull
    @ApiModelProperty(value = "Идентификатор пользователя", required = true)
    private final Long targetUserId;

    @NotNull
    @ApiModelProperty(value = "Идентификатор польщователя", required = true)
    private final Long newUserId;

    @NotNull
    @ApiModelProperty(value = "shiftId", required = true)
    private final Long shiftId;
}

