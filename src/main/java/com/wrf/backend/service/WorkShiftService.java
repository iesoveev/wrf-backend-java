package com.wrf.backend.service;

import com.wrf.backend.db_api.UserDbApi;
import com.wrf.backend.db_api.WorkShiftDbApi;
import com.wrf.backend.db_api.repository.EventRepository;
import com.wrf.backend.db_api.repository.UserRepository;
import com.wrf.backend.db_api.repository.WSRepository;
import com.wrf.backend.entity.*;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.ErrorMessage;
import com.wrf.backend.mapper.EventMapper;
import com.wrf.backend.model.request.*;
import com.wrf.backend.model.response.EventDTO;
import com.wrf.backend.model.response.GeneralIdDTO;
import com.wrf.backend.model.response.PushEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.wrf.backend.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class WorkShiftService {

    final AuthService authService;

    final UserDbApi userDbApi;

    final WorkShiftDbApi workShiftDbApi;

    final FcmService fcmService;

    final EventRepository eventRepository;

    public GeneralIdDTO openWS(final ShiftRequestModel model) {
        final List<User> members = userDbApi.findUsers(model.getMemberIds());
        final var ws = new WorkShift();
        ws.setMembers(members);
        ws.setOpen_at(new Date());
        ws.setUserOpenedId(authService.getUserInfo().getId());

        final var user = userDbApi.findById(authService.getUserInfo().getId());

        fcmService.buildAndSendPush(getReceivers(ws, user.getDeviceToken()),
                user.getFullName(), PushEvent.WS_OPEN, null);
        return new GeneralIdDTO(workShiftDbApi.save(ws).getId());
    }

    public void addMember(final AddMemberToWSModel model) {
        final var workShift = workShiftDbApi.findById(model.getShiftId());
        final var user = userDbApi.findById(model.getUserId());

        if (workShift.getMembers().contains(user))
            throw new BusinessException(USER_IS_ALREADY_ON_THE_SHIFT);

        workShift.getMembers().add(user);

        workShiftDbApi.save(workShift);
    }

    public void replaceMember(final ReplaceMemberModel model) {
        final var workShift = workShiftDbApi.findById(model.getShiftId());
        final var targetUser = userDbApi.findById(model.getTargetUserId());
        final var newUser = userDbApi.findById(model.getNewUserId());
        workShift.getMembers().remove(targetUser);
        workShift.getMembers().add(newUser);

        workShiftDbApi.save(workShift);
    }

    @Transactional
    public void addEvent(final EventModel model) {
        final var workShift = workShiftDbApi.findById(model.getShiftId());

        final var event = new Event();
        event.setText(model.getText());
        event.setWorkShift(workShift);
        event.setWorkShiftId(workShift.getId());
        final var user = userDbApi.findById(authService.getUserInfo().getId());
        event.setUser(user);
        event.setUserId(user.getId());
        eventRepository.save(event);

        workShift.getEvents().add(event);

        workShiftDbApi.save(workShift);

        fcmService.buildAndSendPush(getReceivers(workShift, user.getDeviceToken()),
                user.getFullName(), PushEvent.NEW_EVENT, event.getText());
    }

    public List<EventDTO> getEvents(@Nullable final Long shiftId) {
        return workShiftDbApi
                .findEvents(shiftId)
                .stream()
                .map(EventMapper.INSTANCE::map)
                .collect(Collectors.toUnmodifiableList());
    }

    public void closeWS(final GeneralIdModel model) {
        final var workShift = workShiftDbApi.findById(model.getId());

        if (workShift.getClose_at() != null)
            throw new BusinessException(SHIFT_IS_ALREADY_CLOSED);

        final var user = userDbApi.findById(authService.getUserInfo().getId());

        workShift.setClose_at(new Date());
        workShift.setUserClosedId(user.getId());
        workShiftDbApi.save(workShift);

        fcmService.buildAndSendPush(getReceivers(workShift, user.getDeviceToken()),
                user.getFullName(), PushEvent.WS_CLOSE, null);

    }

    public List<EventDTO> search(final String text,
                                 final Integer limit,
                                 final Integer offset) {
        return workShiftDbApi
                .findEventsByText(text, limit, offset)
                .stream()
                .map(EventMapper.INSTANCE::map)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<String> getReceivers(final WorkShift workShift, final String deviceToken) {
        return workShift
                .getMembers()
                .stream()
                .map(User::getDeviceToken)
                .filter(token -> !token.equals(deviceToken))
                .collect(Collectors.toUnmodifiableList());
    }
}
