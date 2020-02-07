package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel {

    @ApiModelProperty(required = true, value = "Телефон")
    @JsonProperty(value = "phone")
    private String phone;

    @ApiModelProperty(required = true, value = "Пароль")
    @JsonProperty(value = "password")
    private String password;
}
