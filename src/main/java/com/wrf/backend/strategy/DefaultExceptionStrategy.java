package com.wrf.backend.strategy;

import com.wrf.backend.model.response.Response;
import com.wrf.backend.exception.ErrorCode;

public class DefaultExceptionStrategy implements ExceptionStrategy {

    @Override
    public Response process(Exception e) {
        LOG.error(e);
        return new Response(ErrorCode.INTERNAL.getCode(), ErrorCode.INTERNAL.getMessage());
    }
}
