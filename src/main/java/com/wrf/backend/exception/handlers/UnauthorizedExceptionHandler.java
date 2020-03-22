package com.wrf.backend.exception.handlers;

import com.wrf.backend.exception.ErrorMessage;
import com.wrf.backend.exception.UnauthorizedException;
import com.wrf.backend.model.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnauthorizedExceptionHandler {

    private static final Logger log = LogManager.getLogger(UnauthorizedExceptionHandler.class);

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Response handleUnauthorizedException(UnauthorizedException ex) {
        log.warn(ex);
        return new Response(ErrorMessage.NO_AUTHORIZATION);
    }
}
