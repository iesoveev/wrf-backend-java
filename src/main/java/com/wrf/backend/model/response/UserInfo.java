package com.wrf.backend.model.response;

public final class UserInfo {

    private final String phone;

    private final String id;

    public UserInfo(String phone, String id) {
        this.phone = phone;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }
}
