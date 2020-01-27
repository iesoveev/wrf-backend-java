package com.wrf.backend.strategy;

import com.wrf.backend.exception.ErrorMessage;
import com.wrf.backend.model.response.Response;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class DefaultExceptionStrategy implements ExceptionStrategy {

    @Override
    public Response process(final Exception e, final HttpServletResponse response) {
        LOG.error(e);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new Response(ErrorMessage.INTERNAL);
    }
}
