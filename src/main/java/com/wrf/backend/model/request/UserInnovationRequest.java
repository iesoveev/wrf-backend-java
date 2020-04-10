package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInnovationRequest {

    @NotNull
    @ApiModelProperty(required = true, value = "Идентификатор категории")
    private Long categoryId;

    @NotBlank
    @ApiModelProperty(required = true, value = "Текст инновации")
    private String text;

    @NotBlank
    @ApiModelProperty(required = true, value = "Картинка (Base64)")
    private String image;
}
