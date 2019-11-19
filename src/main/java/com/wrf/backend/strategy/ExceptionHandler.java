package com.wrf.backend.strategy;

import com.wrf.backend.model.response.Response;

public class ExceptionHandler {

    private ExceptionStrategy exceptionStrategy;

    public void setExceptionStrategy(ExceptionStrategy exceptionStrategy) {
        this.exceptionStrategy = exceptionStrategy;
    }

    public Response process(Exception e) {
        return this.exceptionStrategy.process(e);
    }
}
