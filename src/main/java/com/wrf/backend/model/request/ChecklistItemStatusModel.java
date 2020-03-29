package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistItemStatusModel {

    @NotNull
    @ApiModelProperty(value = "Идентификатор таски чек-листа", required = true)
    private Long itemId;

    @NotBlank
    @ApiModelProperty(value = "Статус", required = true)
    private String status;
}
