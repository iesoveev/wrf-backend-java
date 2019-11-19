package com.wrf.backend.exception;

public enum ErrorCode {

    PARAM_EXCEPTION(1, "Ошибка входящего запроса"),
    RECORD_EXIST(3, "Запись уже существует"),
    INTERNAL(500,"Внутрення ошибка сервера"),
    ACCESS_DENIED(4, "Доступ запрещен"),
    RECORD_NOT_FOUND(5, "Запись не найдена"),
    UPLOAD_FILE_EXCEPTION(6, "Не удалось сохранить фото");

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
