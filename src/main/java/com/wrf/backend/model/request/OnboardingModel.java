package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public final class OnboardingModel {

    @NotBlank
    @ApiModelProperty(required = true, value = "Тип онбоардинга")
    private final String type;

    @NotBlank
    @ApiModelProperty(required = true, value = "Заголовок")
    private final String title;

    @NotBlank
    @ApiModelProperty(required = true, value = "Текст")
    private final String text;

    @NotBlank
    @ApiModelProperty(required = true, value = "Картинка")
    private final String image;
}
