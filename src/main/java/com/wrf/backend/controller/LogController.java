package com.wrf.backend.controller;

import com.wrf.backend.model.request.AndroidLogModel;
import com.wrf.backend.model.request.GeneralPeriodModel;
import com.wrf.backend.model.response.AndroidLogDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.service.LogService;
import com.wrf.backend.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {

    final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PutMapping("/push")
    public Response pushLog(@RequestBody AndroidLogModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        logService.pushLog(model.getMessage());
        return new Response();
    }

    @GetMapping("/get")
    public List<AndroidLogDTO> getLog(@RequestBody GeneralPeriodModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        return logService.getLog(model);
    }
}
