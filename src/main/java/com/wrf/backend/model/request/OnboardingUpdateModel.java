package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public final class OnboardingUpdateModel {

    @NotBlank
    @ApiModelProperty(value = "type")
    private final String type;

    @ApiModelProperty(value = "title")
    private final String title;

    @ApiModelProperty(value = "text")
    private final String text;

    @ApiModelProperty(value = "image")
    private final String image;
}
