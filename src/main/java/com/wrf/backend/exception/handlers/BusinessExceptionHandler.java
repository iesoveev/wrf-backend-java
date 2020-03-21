package com.wrf.backend.exception.handlers;

import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    private static final Logger log = LogManager.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Response handleBusinessException(BusinessException ex) {
        log.warn(ex);
        return new Response(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleOtherExceptions(Exception ex) {
        log.warn(ex);
        // fixme на этапе разработки оставим вывод любых ошибок в ответ пользаку.
        return new Response(ex.getLocalizedMessage());
    }
}
