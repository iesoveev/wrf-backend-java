package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class ImageRequestModel {

    @ApiModelProperty(value = "Картинка", required = true)
    @JsonProperty(value = "image")
    private String image;

    @ApiModelProperty(value = "Идентификатор инновации", required = true)
    @JsonProperty(value = "id")
    private String id;

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

}
