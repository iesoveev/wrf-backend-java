package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class LoginModel {

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
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
