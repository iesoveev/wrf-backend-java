package com.wrf.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wrf.backend.model.request.LoginModel;
import com.wrf.backend.model.request.UserRegistrationModel;
import com.wrf.backend.service.AuthService;
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
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Value("${base.url}")
    String baseUrl;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @Test
    public void addUserTest() throws Exception {
        var model = new UserRegistrationModel(
                "Илья", "Есовеев",
                "79828765588", "1234567");

        mockMvc.perform(post(baseUrl + "users")
                .header("deviceToken", "123456")
                .contentType(MediaType.  APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().isOk());
    }

    @Test
    public void failAddUserTest() throws Exception {
        var model = new UserRegistrationModel(
                "Илья", null,
                "7982876558", "1234567");

        mockMvc.perform(post(baseUrl + "users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginTest() throws Exception {
        var model = new LoginModel("79828156297", "123456");

        mockMvc.perform(post(baseUrl + "auth/login")
                .header("deviceToken", "123456")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().isOk());
    }

    @Test
    public void failLoginTest() throws Exception {
        var model = new LoginModel("7982815678", "1234567");

        mockMvc.perform(post(baseUrl + "users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(model)))
                .andExpect(status().isBadRequest());
    }


}
