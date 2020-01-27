package com.wrf.backend.exception;

public final class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(ErrorMessage error) {
        super(error.getMessage());
    }

}
