package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class OnboardingModel {

    @ApiModelProperty(required = true, value = "Тип онбоардинга")
    @JsonProperty(value = "type")
    private String type;

    @ApiModelProperty(required = true, value = "Заголовок")
    @JsonProperty(value = "title")
    private String title;

    @ApiModelProperty(required = true, value = "Текст")
    @JsonProperty(value = "text")
    private String text;

    @ApiModelProperty(required = true, value = "Картинка")
    @JsonProperty(value = "image")
    private String image;
}
