package com.wrf.backend.strategy;

import com.wrf.backend.model.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class HttpRequestMethodNotSupportedExceptionStrategy implements ExceptionStrategy {

    @Override
    public Response process(Exception e, HttpServletResponse response) {
        LOG.error(e);
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return new Response(e.getMessage());
    }
}
