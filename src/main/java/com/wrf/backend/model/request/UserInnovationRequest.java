package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public final class UserInnovationRequest {

    @NotBlank
    @ApiModelProperty(required = true, value = "Идентификатор категории")
    private final String categoryId;

    @NotBlank
    @ApiModelProperty(required = true, value = "Текст инновации")
    private final String text;
}
