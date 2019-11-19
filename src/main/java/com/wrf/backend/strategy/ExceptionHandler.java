package com.wrf.backend.strategy;

import com.wrf.backend.model.response.Response;

import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler {

    private ExceptionStrategy exceptionStrategy;

    public void setExceptionStrategy(ExceptionStrategy exceptionStrategy) {
        this.exceptionStrategy = exceptionStrategy;
    }

    public Response process(Exception e, HttpServletResponse response) {
        return this.exceptionStrategy.process(e, response);
    }
}
