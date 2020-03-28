package com.wrf.backend.controller;

import com.wrf.backend.model.request.*;
import com.wrf.backend.model.response.EventDTO;
import com.wrf.backend.model.response.GeneralIdDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.service.WorkShiftService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ws", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkShiftController {

    private final WorkShiftService workShiftService;

    public WorkShiftController(WorkShiftService workShiftService) {
        this.workShiftService = workShiftService;
    }

    @ApiOperation(value = "Открытие смены")
    @PutMapping("/open")
    public GeneralIdDTO openWS(@Valid @RequestBody ShiftRequestModel model) {
        return workShiftService.openWS(model);
    }

    @ApiOperation(value = "Добавление пользователя в смену")
    @PostMapping("/members")
    public Response addMember(@Valid @RequestBody AddMemberToWSModel model) {
        workShiftService.addMember(model);
        return new Response();
    }

    @ApiOperation(value = "Замена члена команды")
    @PostMapping("/replace")
    public Response replaceMember(@Valid @RequestBody ReplaceMemberModel model) {
        workShiftService.replaceMember(model);
        return new Response();
    }

    @ApiOperation(value = "Закрытие смены")
    @PostMapping("/close")
    public Response closeWS(@Valid @RequestBody GeneralIdModel model) {
        workShiftService.closeWS(model);
        return new Response();
    }

    @ApiOperation(value = "Добавление события в смену")
    @PutMapping("/event")
    public Response addEvent(@Valid @RequestBody EventModel model) {
        workShiftService.addEvent(model);
        return new Response();
    }

    @ApiOperation(value = "Получение событый смены")
    @GetMapping("/{shiftId}/events")
    public List<EventDTO> getEvents(@PathVariable Long shiftId) {
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
