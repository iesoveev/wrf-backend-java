package com.wrf.backend.service;

import com.wrf.backend.DbApi;
import com.wrf.backend.entity.*;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.request.*;
import com.wrf.backend.model.response.EventDTO;
import com.wrf.backend.model.response.GeneralIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

import static com.wrf.backend.exception.ErrorMessage.*;
import static com.wrf.backend.utils.DateUtils.*;

@Service
public class WorkShiftService {

    final HibernateTemplate hibernateTemplate;

    final AuthService authService;

    final DbApi dbApi;

    @Autowired
    public WorkShiftService(HibernateTemplate hibernateTemplate, AuthService authService, DbApi dbApi) {
        this.hibernateTemplate = hibernateTemplate;
        this.authService = authService;
        this.dbApi = dbApi;
    }

    @Transactional
    public GeneralIdDTO openWS(final ShiftRequestModel model) {
        List<User> members = dbApi.getUsers(model.getMemberIds());
        var ws = new WorkShift();
        ws.setMembers(members);
        ws.setOpenedTime(new Date());
        ws.setUserOpenedId(authService.getUserInfo().getId());

        var id = (String) hibernateTemplate.save(ws);

        return new GeneralIdDTO(id);
    }

    @Transactional
    public void addMember(final AddMemberToWSModel model) {
        var workShift = dbApi.getWorkShift(model.getShiftId());
        var user = dbApi.getUser(model.getUserId());

        if (workShift.getMembers().contains(user))
            throw new BusinessException(USER_IS_ALREADY_ON_THE_SHIFT);

        // members не может быть null
        workShift.getMembers().add(user);

        hibernateTemplate.update(workShift);
    }

    @Transactional
    public void replaceMember(final ReplaceMemberModel model) {
        var workShift = dbApi.getWorkShift(model.getShiftId());
        var targetUser = dbApi.getUser(model.getTargetUserId());
        var newUser = dbApi.getUser(model.getNewUserId());
        workShift.getMembers().remove(targetUser);
        workShift.getMembers().add(newUser);

        hibernateTemplate.update(workShift);
    }

    @Transactional
    public void addEvent(final EventModel model) {
        var workShift = dbApi.getWorkShift(model.getShiftId());

        var event = new Event();
        event.setText(model.getText());
        event.setWorkShift(workShift);
        event.setWorkShiftId(workShift.getId());
        var user = dbApi.getUser(authService.getUserInfo().getId());
        event.setUser(user);
        event.setUserId(user.getId());
        hibernateTemplate.save(event);

        workShift.getEvents().add(event);

        hibernateTemplate.update(workShift);
    }

    @Transactional
    public List<EventDTO> getEvents(final String shiftId) {
        var events = dbApi.getEvents(shiftId);
        events.forEach( event -> {
            event.setCreatedTime(dateTimeFormatter.format(event.getCreatedDateTime()));
            event.setCreatedDateTime(null);
        });
        return events;
    }

    @Transactional
    public void closeWS(final GeneralIdModel model) {
        var workShift = dbApi.getWorkShift(model.getId());

        if (workShift.getClosedTime() != null)
            throw new BusinessException(SHIFT_IS_ALREADY_CLOSED);

        workShift.setClosedTime(new Date());
        workShift.setUserClosedId(authService.getUserInfo().getId());
        hibernateTemplate.update(workShift);
    }

    @Transactional(readOnly = true)
    public List<EventDTO> search(final String text) {
        var events = dbApi.getEventsByText(text);
        events.forEach( event -> {
            event.setCreatedTime(dateTimeFormatter.format(event.getCreatedDateTime()));
            event.setCreatedDateTime(null);
        });
        return events;
    }
}
