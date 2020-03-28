package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OnboardingModel {

    @NotBlank
    @ApiModelProperty(required = true, value = "Тип онбоардинга")
    private String type;

    @NotBlank
    @ApiModelProperty(required = true, value = "Заголовок")
    private String title;

    @NotBlank
    @ApiModelProperty(required = true, value = "Текст")
    private String text;

    @NotBlank
    @ApiModelProperty(required = true, value = "Картинка (Base64)")
    private String image;
}
