package com.wrf.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    private static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);

    private static final String SERVICE_NAME = "wrf-service";
    private static final String WELCOME_PHRASE = "Welcome to " + SERVICE_NAME;

    @RequestMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException {
        response.getWriter().print(WELCOME_PHRASE);
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> ping(HttpServletRequest request) {
        Map<String, Object> res = new HashMap<>();
        res.put("date", dateTimeFormatter.format(new Date()));
        res.put("value", "pong");
        res.put("service", SERVICE_NAME);
        res.put("requestURI", request.getRequestURI());
        return res;
    }

}
