package com.wrf.backend;

import com.wrf.backend.exception.UnauthorizedException;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.strategy.DefaultExceptionStrategy;
import com.wrf.backend.strategy.BusinessExceptionStrategy;
import com.wrf.backend.strategy.UnauthorizedExceptionStratedy;
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
    public ModelAndView resolveException(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                         Object o, Exception e) {

        com.wrf.backend.strategy.ExceptionHandler exceptionHandler = new com.wrf.backend.strategy.ExceptionHandler();

        if (e instanceof BusinessException)
            exceptionHandler.setExceptionStrategy(new BusinessExceptionStrategy());
        else if (e instanceof UnauthorizedException)
            exceptionHandler.setExceptionStrategy(new UnauthorizedExceptionStratedy());
        else
            exceptionHandler.setExceptionStrategy(new DefaultExceptionStrategy());


        Response response = exceptionHandler.process(e, httpResponse);

        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType(HeaderConst.CONTENT_TYPE);
        try {
            httpResponse.getWriter().print(response);
        } catch (IOException ex) {
            return null;
        }

        return new ModelAndView();
    }
}
