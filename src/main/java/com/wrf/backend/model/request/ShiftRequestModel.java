package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class ShiftRequestModel {

    @NotEmpty
    @ApiModelProperty(value = "Идентификаторы участников команды", required = true)
    private List<Long> memberIds;
}
