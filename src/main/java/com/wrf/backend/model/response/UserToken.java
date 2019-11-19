package com.wrf.backend.model.response;

import java.util.Date;

public final class UserToken {

    private final String phone;
    private final Date expirationTime;

    public UserToken(String phone, Date expirationTime) {
        this.phone = phone;
        this.expirationTime = expirationTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public String getPhone() {
        return phone;
    }
}
