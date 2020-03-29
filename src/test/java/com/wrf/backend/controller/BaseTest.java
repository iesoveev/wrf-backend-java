package com.wrf.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wrf.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public abstract class BaseTest {

    @Value("${base.url}")
    String baseUrl;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    static final ResultMatcher isOk = MockMvcResultMatchers.status().isOk();
    static final ResultMatcher isBadRequest = MockMvcResultMatchers.status().is4xxClientError();

    void post(final String path, final Object model, final ResultMatcher resStatus) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("deviceToken", "123456")
                .content(JSON.toJSONString(model)))
                .andExpect(resStatus);
    }

    void put(final String path, final Object model, final ResultMatcher resStatus) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(resStatus);
    }
}
