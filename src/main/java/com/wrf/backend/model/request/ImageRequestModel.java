package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestModel {

    @NotBlank
    @ApiModelProperty(value = "Картинка", required = true)
    private String image;

    @NotNull
    @ApiModelProperty(value = "Идентификатор сущности", required = true)
    private Long id;
}
