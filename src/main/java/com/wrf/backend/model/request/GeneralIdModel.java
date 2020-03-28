package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class GeneralIdModel {

    @NotNull
    @ApiModelProperty(value = "Идентификатор", required = true)
    private Long id;
}
