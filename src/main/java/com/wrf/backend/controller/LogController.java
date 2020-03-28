package com.wrf.backend.controller;

import com.wrf.backend.model.request.AndroidLogModel;
import com.wrf.backend.model.request.GeneralPeriodModel;
import com.wrf.backend.model.response.AndroidLogDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.service.LogService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {

    final LogService logService;

    @ApiOperation(value = "Сохранение лога для андроида")
    @PutMapping("/push")
    public Response pushLog(@Valid @RequestBody AndroidLogModel model) {
        logService.pushLog(model.getMessage());
        return new Response();
    }

    @ApiOperation(value = "Получение логов по периоду")
    @GetMapping("/find")
    public List<AndroidLogDTO> findLog(@RequestParam String begin_date,
                                       @RequestParam String end_date) {
        return logService.findLog(begin_date, end_date);
    }
}
