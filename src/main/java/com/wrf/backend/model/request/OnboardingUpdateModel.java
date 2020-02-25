package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class OnboardingUpdateModel {

    @ApiModelProperty(value = "type")
    private String type;

    @ApiModelProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "text")
    private String text;

    @ApiModelProperty(value = "image")
    private String image;
}
