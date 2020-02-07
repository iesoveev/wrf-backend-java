package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class UserRegistrationModel {

    @ApiModelProperty(required = true, value = "Имя")
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(required = true, value = "Фамилия")
    @JsonProperty(value = "surname")
    private String surname;

    @ApiModelProperty(required = true, value = "Телефон")
    @JsonProperty(value = "phone")
    private String phone;

    @ApiModelProperty(required = true, value = "Пароль")
    @JsonProperty(value = "password")
    private String password;
}
