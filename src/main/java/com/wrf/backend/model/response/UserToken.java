package com.wrf.backend.model.response;

import java.util.Date;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToken userToken = (UserToken) o;
        return Objects.equals(phone, userToken.phone) &&
                Objects.equals(expirationTime, userToken.expirationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, expirationTime);
    }
}
