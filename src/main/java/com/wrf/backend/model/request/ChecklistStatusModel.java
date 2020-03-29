package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistStatusModel {

    @NotBlank
    @ApiModelProperty(value = "Статус таски чек-листа", required = true)
    private String status;
}
