package com.wrf.backend.strategy;

import com.wrf.backend.exception.ErrorCode;
import com.wrf.backend.model.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class UnauthorizedExceptionStratedy implements ExceptionStrategy {

    @Override
    public Response process(Exception e, HttpServletResponse response) {
        LOG.error(e);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new Response(ErrorCode.UNAUTHORIZED.getCode(), e.getMessage());
    }
}
