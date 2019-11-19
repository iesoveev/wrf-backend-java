package com.wrf.backend.strategy;

import com.wrf.backend.model.response.Response;
import com.wrf.backend.exception.RestException;

public class RestExceptionStrategy implements ExceptionStrategy {

    @Override
    public Response process(Exception e) {
        LOG.error(e);
        return new Response(((RestException) e).getCode(), e.getMessage());
    }
}
