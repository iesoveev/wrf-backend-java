package com.wrf.backend.controller;

import com.wrf.backend.utils.DateUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@Api(tags = "main")
public class MainController {

    private static final String SERVICE_NAME = "wrf-service";
    private static final String WELCOME_PHRASE = "Welcome to " + SERVICE_NAME;

    @GetMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException {
        response.getWriter().print(WELCOME_PHRASE);
    }

    @GetMapping(value = "/ping")
    public Map<String, Object> ping(HttpServletRequest request) {
        return Map.of(
                "date", DateUtils.dateTimeFormatter.format(new Date()),
                "value", "pong",
                "service", SERVICE_NAME,
                "requestURI", request.getRequestURI()
        );
    }
}
