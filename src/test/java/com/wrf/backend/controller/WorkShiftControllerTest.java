package com.wrf.backend.controller;

import com.wrf.backend.db_api.UserDbApi;
import com.wrf.backend.db_api.WorkShiftDbApi;
import com.wrf.backend.db_api.repository.EventRepository;
import com.wrf.backend.db_api.repository.WSRepository;
import com.wrf.backend.model.request.*;
import com.wrf.backend.service.AsyncService;
import com.wrf.backend.service.WorkShiftService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkShiftController.class)
public class WorkShiftControllerTest extends BaseTest {

    @MockBean
    WorkShiftService workShiftService;

    @MockBean
    UserDbApi userDbApi;

    @MockBean
    WorkShiftDbApi workShiftDbApi;

    @MockBean
    AsyncService asyncService;

    @MockBean
    WSRepository wsRepository;

    @MockBean
    EventRepository eventRepository;

    private static final String wsOpenPath = "ws/open";
    private static final String wsClosePath = "ws/close";
    private static final String wsAddMemberPath = "ws/members";
    private static final String wsReplaceMemberPath = "ws/replace";
    private static final String wsEventPath = "ws/event";


    @Test
    public void openWS() throws Exception {
        var model = new ShiftRequestModel(Arrays.asList(1L, 2L, 3L));
        put(wsOpenPath, model, isOk);
    }

    @Test
    public void failOpenWS() throws Exception {
        put(wsOpenPath, new ShiftRequestModel(), isBadRequest);
    }

    @Test
    public void addMember() throws Exception {
        var model = new AddMemberToWSModel(1L, 1L);
        post(wsAddMemberPath, model, isOk);
    }

    @Test
    public void failAddMember() throws Exception {
        var model = new AddMemberToWSModel(null, 1L);
        post(wsAddMemberPath, model, isBadRequest);
    }

    @Test
    public void replaceMember() throws Exception {
        var model = new ReplaceMemberModel(1L, 2L, 3L);
        post(wsReplaceMemberPath, model, isOk);
    }

    @Test
    public void failReplaceMember() throws Exception {
        var model = new ReplaceMemberModel(1L, null, null);
        post(wsReplaceMemberPath, model, isBadRequest);
    }

    @Test
    public void closeWS() throws Exception {
        var model = new GeneralIdModel(1L);
        post(wsClosePath, model, isOk);
    }

    @Test
    public void failCloseWS() throws Exception {
        var model = new GeneralIdModel();
        post(wsClosePath, model, isBadRequest);
    }

    @Test
    public void addEvent() throws Exception {
        EventModel model = new EventModel(1L, "qwerty");
        put(wsEventPath, model, isOk);
    }

    @Test
    public void failAddEvent() throws Exception {
        EventModel model = new EventModel(null, "qwerty");
        put(wsEventPath, model, isBadRequest);
    }
}
