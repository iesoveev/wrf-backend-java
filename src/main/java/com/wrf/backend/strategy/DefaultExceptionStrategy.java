package com.wrf.backend.strategy;

import com.wrf.backend.model.response.Response;
import com.wrf.backend.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class DefaultExceptionStrategy implements ExceptionStrategy {

    @Override
    public Response process(Exception e, HttpServletResponse response) {
        LOG.error(e);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new Response(ErrorCode.INTERNAL.getCode(), ErrorCode.INTERNAL.getMessage());
    }
}
