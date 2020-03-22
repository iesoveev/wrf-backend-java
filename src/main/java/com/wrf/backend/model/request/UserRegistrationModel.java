package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public final class UserRegistrationModel {

    @NotBlank
    @ApiModelProperty(required = true, value = "Имя")
    private final String name;

    @NotBlank
    @ApiModelProperty(required = true, value = "Фамилия")
    private final String surname;

    @Pattern(regexp = "^((7)([0-9]{10}))$", message = "Неверный формат телефона")
    @ApiModelProperty(required = true, value = "Телефон")
    private final String phone;

    @NotBlank
    @ApiModelProperty(required = true, value = "Пароль")
    private final String password;
}
