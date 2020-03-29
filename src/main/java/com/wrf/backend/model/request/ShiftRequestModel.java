package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftRequestModel {

    @NotEmpty
    @ApiModelProperty(value = "Идентификаторы участников команды", required = true)
    private List<Long> memberIds;
}
