package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestModel {

    @ApiModelProperty(value = "Картинка", required = true)
    private String image;

    @ApiModelProperty(value = "Идентификатор инновации", required = true)
    private Long id;
}
