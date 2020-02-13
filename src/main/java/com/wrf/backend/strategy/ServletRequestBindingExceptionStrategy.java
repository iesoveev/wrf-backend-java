package com.wrf.backend.strategy;

import com.wrf.backend.model.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class ServletRequestBindingExceptionStrategy implements ExceptionStrategy {

    @Override
    public Response process(Exception e, HttpServletResponse response) {
        LOG.error(e);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new Response(e.getMessage());
    }
}
