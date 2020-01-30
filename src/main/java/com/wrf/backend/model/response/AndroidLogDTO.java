package com.wrf.backend.model.response;

public class AndroidLogDTO {

    private String message;

    private String createdTime;

    public String getMessage() {
        return message;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
