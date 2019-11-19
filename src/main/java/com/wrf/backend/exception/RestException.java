package com.wrf.backend.exception;

public final class RestException extends RuntimeException {

    private final Integer code;
    private final String message;

    public RestException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
