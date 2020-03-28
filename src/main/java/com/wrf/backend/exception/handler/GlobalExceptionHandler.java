package com.wrf.backend.exception.handler;

import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.ErrorMessage;
import com.wrf.backend.exception.UnauthorizedException;
import com.wrf.backend.model.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error(ex);
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String field = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(field, message);
                });
        errors.put("success", false);
        return errors;
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response handleServletException(ServletException ex) {
        log.warn(ex);
        return new Response(ex.getLocalizedMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Response handleUnauthorizedException(UnauthorizedException ex) {
        log.warn(ex);
        return new Response(ErrorMessage.NO_AUTHORIZATION);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleOtherExceptions(Exception ex) {
        log.warn(ex);
        // fixme на этапе разработки оставим вывод любых ошибок в ответ пользаку.
        return new Response(ex.getLocalizedMessage());
    }
}
