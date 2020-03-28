package com.wrf.backend.exception;


import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5162710183389028002L;

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
