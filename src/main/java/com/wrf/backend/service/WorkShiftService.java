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
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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

    final WSRepository wsRepository;

    final EventRepository eventRepository;

    final UserRepository userRepository;

    public GeneralIdDTO openWS(final ShiftRequestModel model) {
        final List<User> members = userDbApi.findUsers(model.getMemberIds());
        final var ws = new WorkShift();
        ws.setMembers(members);
        ws.setOpen_at(new Date());
        ws.setUserOpenedId(authService.getUserInfo().getId());

        return new GeneralIdDTO(wsRepository.save(ws).getId());
    }

    public void addMember(final AddMemberToWSModel model) {
        final var workShift = workShiftDbApi.findById(model.getShiftId());
        final var user = userDbApi.findById(model.getUserId());

        if (workShift.getMembers().contains(user))
            throw new BusinessException(USER_IS_ALREADY_ON_THE_SHIFT);

        workShift.getMembers().add(user);

        wsRepository.save(workShift);
    }

    public void replaceMember(final ReplaceMemberModel model) {
        final var workShift = workShiftDbApi.findById(model.getShiftId());
        final var targetUser = userDbApi.findById(model.getTargetUserId());
        final var newUser = userDbApi.findById(model.getNewUserId());
        workShift.getMembers().remove(targetUser);
        workShift.getMembers().add(newUser);

        wsRepository.save(workShift);
    }

    @Transactional
    public void addEvent(final EventModel model) {
        final var workShift = wsRepository.findById(model.getShiftId())
                .orElseThrow(() -> new BusinessException(SHIFT_IS_NOT_FOUND));

        final var event = new Event();
        event.setText(model.getText());
        event.setWorkShift(workShift);
        event.setWorkShiftId(workShift.getId());
        final var user = userRepository.findById(authService.getUserInfo().getId())
                .orElseThrow(() -> new BusinessException(USER_IS_NOT_FOUND));
        event.setUser(user);
        event.setUserId(user.getId());
        eventRepository.save(event);

        workShift.getEvents().add(event);

        wsRepository.save(workShift);

//        List<String> receivers = workShift.getMembers().stream()
//                .map(User::getDeviceToken)
//                .filter(token -> !token.equals(user.getDeviceToken()))
//                .collect(Collectors.toUnmodifiableList());
//
//        asyncService.sendNotify(receivers, event.getText());
    }

    public List<EventDTO> getEvents(@Nullable final String shiftId) {
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

        workShift.setClose_at(new Date());
        workShift.setUserClosedId(authService.getUserInfo().getId());
        wsRepository.save(workShift);
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
}
