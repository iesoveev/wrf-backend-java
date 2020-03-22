package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public final class LoginModel {

    @Pattern(regexp = "^((7)([0-9]{10}))$", message = "Неверный формат телефона")
    @ApiModelProperty(required = true, value = "Телефон")
    private final String phone;

    @NotBlank
    @ApiModelProperty(required = true, value = "Пароль")
    private final String password;
}
