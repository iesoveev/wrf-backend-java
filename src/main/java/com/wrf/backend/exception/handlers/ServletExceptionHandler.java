package com.wrf.backend.exception.handlers;

import com.wrf.backend.model.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.ServletException;

@RestControllerAdvice
public class ServletExceptionHandler {

    private static final Logger log = LogManager.getLogger(ServletExceptionHandler.class);

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response handleBusinessException(ServletException ex) {
        log.warn(ex);
        return new Response(ex.getLocalizedMessage());
    }
}
