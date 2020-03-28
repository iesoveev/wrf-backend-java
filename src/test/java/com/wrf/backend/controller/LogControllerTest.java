package com.wrf.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wrf.backend.model.request.AndroidLogModel;
import com.wrf.backend.model.request.GeneralPeriodModel;
import com.wrf.backend.service.AuthService;
import com.wrf.backend.service.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LogController.class)
public class LogControllerTest {

    @Value("${base.url}")
    String baseUrl;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @MockBean
    LogService logService;

    @Test
    public void pushLog() throws Exception {
        var model = new AndroidLogModel("AAAAAAAAAAAAA");

        mockMvc.perform(put(baseUrl + "log/push")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().isOk());
    }

    @Test
    public void failPushLog() throws Exception {
        var model = new AndroidLogModel(null);

        mockMvc.perform(put(baseUrl + "log/push")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void findLog() throws Exception {

        mockMvc.perform(get(baseUrl + "log/find")
                .queryParam("beginDate", "12.12.2012 12:12:12")
                .queryParam("endDate", "12.12.2012 12:12:12"));
    }
}