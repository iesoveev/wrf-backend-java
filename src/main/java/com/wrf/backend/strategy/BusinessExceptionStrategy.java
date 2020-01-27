package com.wrf.backend.strategy;

import com.wrf.backend.model.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class BusinessExceptionStrategy implements ExceptionStrategy {

    @Override
    public Response process(final Exception e, final HttpServletResponse response) {
        LOG.error(e);
        response.setStatus(HttpStatus.OK.value());
        return new Response(e.getMessage());
    }
}
