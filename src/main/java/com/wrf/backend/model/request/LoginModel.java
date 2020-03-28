package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {

    @Pattern(regexp = "^((7)([0-9]{10}))$", message = "Неверный формат телефона")
    @ApiModelProperty(required = true, value = "Телефон")
    private String phone;

    @NotBlank
    @ApiModelProperty(required = true, value = "Пароль")
    private String password;
}
