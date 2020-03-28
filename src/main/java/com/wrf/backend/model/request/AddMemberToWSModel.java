package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberToWSModel {

    @NotNull
    @ApiModelProperty(value = "userId", required = true)
    private Long userId;

    @NotNull
    @ApiModelProperty(value = "shiftId", required = true)
    private Long shiftId;
}
