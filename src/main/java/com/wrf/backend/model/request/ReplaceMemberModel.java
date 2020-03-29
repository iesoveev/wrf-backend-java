package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplaceMemberModel {

    @NotNull
    @ApiModelProperty(value = "Идентификатор пользователя", required = true)
    private Long targetUserId;

    @NotNull
    @ApiModelProperty(value = "Идентификатор польщователя", required = true)
    private Long newUserId;

    @NotNull
    @ApiModelProperty(value = "shiftId", required = true)
    private Long shiftId;
}

