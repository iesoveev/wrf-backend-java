package com.wrf.backend.controller;

import com.wrf.backend.utils.DateUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
public class MainController {

    private static final String SERVICE_NAME = "wrf-service";
    private static final String WELCOME_PHRASE = "Welcome to " + SERVICE_NAME;

    @RequestMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException {
        response.getWriter().print(WELCOME_PHRASE);
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> ping(HttpServletRequest request) {
        return Map.of(
                "date", DateUtils.dateTimeFormatter.format(new Date()),
                "value", "pong",
                "service", SERVICE_NAME,
                "requestURI", request.getRequestURI()
        );
    }
}
