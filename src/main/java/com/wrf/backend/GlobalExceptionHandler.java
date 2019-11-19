package com.wrf.backend;

import com.wrf.backend.exception.RestException;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.strategy.DefaultExceptionStrategy;
import com.wrf.backend.strategy.RestExceptionStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    @ExceptionHandler(Exception.class)
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

        com.wrf.backend.strategy.ExceptionHandler exceptionHandler = new com.wrf.backend.strategy.ExceptionHandler();

        if (e instanceof RestException) {
            exceptionHandler.setExceptionStrategy(new RestExceptionStrategy());
        } else {
            exceptionHandler.setExceptionStrategy(new DefaultExceptionStrategy());
        }

        Response response = exceptionHandler.process(e);

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(HeaderConst.CONTENT_TYPE);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        try {
            httpServletResponse.getWriter().print(response);
        } catch (IOException ex) {
            return null;
        }

        return new ModelAndView();
    }
}
