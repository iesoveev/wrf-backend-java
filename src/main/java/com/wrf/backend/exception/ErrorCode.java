package com.wrf.backend.exception;

public enum ErrorCode {

    INTERNAL(500,"Внутрення ошибка сервера"),
    UNAUTHORIZED(401, "Нет авторизации"),
    OK(200, "ОК");

    private String message;
    private Integer code;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
