package com.wrf.backend.controller;

import com.wrf.backend.model.request.AndroidLogModel;
import com.wrf.backend.model.request.GeneralPeriodModel;
import com.wrf.backend.model.response.AndroidLogDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.service.LogService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {

    final LogService logService;

    final HibernateTemplate hibernateTemplate;

    @ApiOperation(value = "Сохранение лога для андроида")
    @PutMapping("/push")
    public Response pushLog(@RequestBody AndroidLogModel model) {
        logService.pushLog(model.getMessage());
        return new Response();
    }

    @ApiOperation(value = "Получение логов по периоду")
    @GetMapping("/find")
    public List<AndroidLogDTO> findLog(@RequestBody GeneralPeriodModel model) {
        return logService.findLog(model);
    }
}
