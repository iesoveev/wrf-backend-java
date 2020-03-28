package com.wrf.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wrf.backend.model.request.UserInnovationRequest;
import com.wrf.backend.service.AuthService;
import com.wrf.backend.service.InnovationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InnovationController.class)
public class InnovationControllerTest {

    @Value("${base.url}")
    String baseUrl;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @MockBean
    InnovationService innovationService;

    @Test
    public void addInnovationTest() throws Exception {
        var model = new UserInnovationRequest(1L, "123456");

        mockMvc.perform(post(baseUrl + "innovations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().isOk());
    }

    @Test
    public void failAddInnovationTest() throws Exception {
        var model = new UserInnovationRequest(null, null);

        mockMvc.perform(post(baseUrl + "innovations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().is4xxClientError());
    }
}
