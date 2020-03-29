package com.wrf.backend.controller;

import com.wrf.backend.model.request.AndroidLogModel;
import com.wrf.backend.service.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(LogController.class)
public class LogControllerTest extends BaseTest {

    @MockBean
    LogService logService;

    @Test
    public void pushLog() throws Exception {
        var model = new AndroidLogModel("AAAAAAAAAAAAA");
        put("log/push", model, isOk);
    }

    @Test
    public void failPushLog() throws Exception {
        var model = new AndroidLogModel(null);
        put("log/push", model, isBadRequest);
    }
}