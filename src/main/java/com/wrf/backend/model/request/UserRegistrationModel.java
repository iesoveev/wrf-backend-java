package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

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

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
