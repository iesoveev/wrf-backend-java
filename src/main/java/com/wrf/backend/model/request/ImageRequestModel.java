package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class ImageRequestModel {

    @ApiModelProperty(value = "Картинка", required = true)
    private final String image;

    @ApiModelProperty(value = "Идентификатор инновации", required = true)
    private final String id;
}
