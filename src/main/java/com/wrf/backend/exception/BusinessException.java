package com.wrf.backend.exception;

import java.util.function.Supplier;

public final class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ErrorMessage error) {
        super(error.getMessage());
    }

    public BusinessException() {
        super();
    }

}
