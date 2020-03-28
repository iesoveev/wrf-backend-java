package com.wrf.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wrf.backend.model.request.OnboardingModel;
import com.wrf.backend.service.AuthService;
import com.wrf.backend.service.OnboardingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OnboardingController.class)
public class OnboardingControllerTest {

    @Value("${base.url}")
    String baseUrl;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @MockBean
    OnboardingService onboardingService;

    @Test
    public void addOnboardingTest() throws Exception {
        var model = new OnboardingModel("ASD", "qwert",
                "qwerty", "qwerty");

        mockMvc.perform(put(baseUrl + "onboarding/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().isOk());

    }

    @Test
    public void failAddOnboardingTest() throws Exception {
        var model = new OnboardingModel(null, null,
                "qwerty", "qwerty");

        mockMvc.perform(put(baseUrl + "onboarding/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().is4xxClientError());

    }
}
