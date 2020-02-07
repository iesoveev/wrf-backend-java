package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ImageRequestModel {

    @ApiModelProperty(value = "Картинка", required = true)
    @JsonProperty(value = "image")
    private String image;

    @ApiModelProperty(value = "Идентификатор инновации", required = true)
    @JsonProperty(value = "id")
    private String id;
}
