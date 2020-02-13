package com.wrf.backend.controller;

import com.wrf.backend.model.request.*;
import com.wrf.backend.model.response.EventDTO;
import com.wrf.backend.model.response.GeneralIdDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.service.WorkShiftService;
import com.wrf.backend.utils.ValidationUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/ws", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkShiftController {

    private final WorkShiftService workShiftService;

    @Autowired
    public WorkShiftController(WorkShiftService workShiftService) {
        this.workShiftService = workShiftService;
    }


    @ApiOperation(value = "Открытие смены")
    @PutMapping("/open")
    public GeneralIdDTO openWS(@RequestBody ShiftRequestModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        return workShiftService.openWS(model);
    }

    @ApiOperation(value = "Добавление пользователя в смену")
    @PostMapping("/members")
    public Response addMember(@RequestBody AddMemberToWSModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        workShiftService.addMember(model);
        return new Response();
    }

    @ApiOperation(value = "Замена члена команды")
    @PostMapping("/replace")
    public Response replaceMember(@RequestBody ReplaceMemberModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        workShiftService.replaceMember(model);
        return new Response();
    }

    @ApiOperation(value = "Закрытие смены")
    @PostMapping("/close")
    public Response closeWS(@RequestBody GeneralIdModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        workShiftService.closeWS(model);
        return new Response();
    }

    @ApiOperation(value = "Добавление события в смену")
    @PutMapping("/event")
    public Response addEvent(@RequestBody EventModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        workShiftService.addEvent(model);
        return new Response();
    }

    @ApiOperation(value = "Получение событый смены")
    @GetMapping("/{shiftId}/events")
    public List<EventDTO> getEvents(@PathVariable String shiftId) {
        return workShiftService.getEvents(shiftId);
    }

    @ApiOperation(value = "Поиск событий по тексу")
    @GetMapping("/search/events")
    public List<EventDTO> searchEventsByText(@RequestParam String text,
                                             @RequestParam Integer limit,
                                             @RequestParam Integer offset)
    {
        return workShiftService.search(text, limit, offset);
    }
}
