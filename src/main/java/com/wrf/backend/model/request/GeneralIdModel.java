package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
public class GeneralIdModel {

    @NotBlank
    @ApiModelProperty(value = "Идентификатор", required = true)
    private String id;
}
