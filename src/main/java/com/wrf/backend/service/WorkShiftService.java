package com.wrf.backend.service;

import com.wrf.backend.db_api.UserDbApi;
import com.wrf.backend.db_api.WorkShiftDbApi;
import com.wrf.backend.entity.*;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.request.*;
import com.wrf.backend.model.response.EventDTO;
import com.wrf.backend.model.response.GeneralIdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

import static com.wrf.backend.exception.ErrorMessage.*;
import static com.wrf.backend.utils.DateUtils.*;

@Service
@RequiredArgsConstructor
public class WorkShiftService {

    final HibernateTemplate hibernateTemplate;

    final AuthService authService;

    final UserDbApi userDbApi;

    final WorkShiftDbApi workShiftDbApi;

    final AsyncService asyncService;

    @Transactional
    public GeneralIdDTO openWS(final ShiftRequestModel model) {
        final List<User> members = userDbApi.findUsers(model.getMemberIds());
        final var ws = new WorkShift();
        ws.setMembers(members);
        ws.setOpenedTime(new Date());
        ws.setUserOpenedId(authService.getUserInfo().getId());

        var id = (String) hibernateTemplate.save(ws);

        return new GeneralIdDTO(id);
    }

    @Transactional
    public void addMember(final AddMemberToWSModel model) {
        final var workShift = workShiftDbApi.findWorkShift(model.getShiftId());
        final var user = userDbApi.findUser(model.getUserId());

        if (workShift.getMembers().contains(user))
            throw new BusinessException(USER_IS_ALREADY_ON_THE_SHIFT);

        // members не может быть null
        workShift.getMembers().add(user);

        hibernateTemplate.update(workShift);
    }

    @Transactional
    public void replaceMember(final ReplaceMemberModel model) {
        final var workShift = workShiftDbApi.findWorkShift(model.getShiftId());
        final var targetUser = userDbApi.findUser(model.getTargetUserId());
        final var newUser = userDbApi.findUser(model.getNewUserId());
        workShift.getMembers().remove(targetUser);
        workShift.getMembers().add(newUser);

        hibernateTemplate.update(workShift);
    }

    @Transactional
    public void addEvent(final EventModel model) {
        final var workShift = workShiftDbApi.findWorkShift(model.getShiftId());

        final var event = new Event();
        event.setText(model.getText());
        event.setWorkShift(workShift);
        event.setWorkShiftId(workShift.getId());
        final var user = userDbApi.findUser(authService.getUserInfo().getId());
        event.setUser(user);
        event.setUserId(user.getId());
        hibernateTemplate.save(event);

        workShift.getEvents().add(event);

        hibernateTemplate.update(workShift);

//        List<String> receivers = workShift.getMembers().stream()
//                .map(User::getDeviceToken)
//                .filter(token -> !token.equals(user.getDeviceToken()))
//                .collect(Collectors.toUnmodifiableList());
//
//        asyncService.sendNotify(receivers, event.getText());
    }

    @Transactional
    public List<EventDTO> getEvents(@Nullable final String shiftId) {
        final var events = workShiftDbApi.findEvents(shiftId);
        events.forEach( event -> {
            event.setCreatedTime(dateTimeFormatter.format(event.getCreatedDateTime()));
            event.setCreatedDateTime(null);
        });
        return events;
    }

    @Transactional
    public void closeWS(final GeneralIdModel model) {
        final var workShift = workShiftDbApi.findWorkShift(model.getId());

        if (workShift.getClosedTime() != null)
            throw new BusinessException(SHIFT_IS_ALREADY_CLOSED);

        workShift.setClosedTime(new Date());
        workShift.setUserClosedId(authService.getUserInfo().getId());
        hibernateTemplate.update(workShift);
    }

    @Transactional(readOnly = true)
    public List<EventDTO> search(final String text,
                                 final Integer limit,
                                 final Integer offset) {
        final var events = workShiftDbApi.findEventsByText(text, limit, offset);
        events.forEach( event -> {
            event.setCreatedTime(dateTimeFormatter.format(event.getCreatedDateTime()));
            event.setCreatedDateTime(null);
        });
        return events;
    }
}
