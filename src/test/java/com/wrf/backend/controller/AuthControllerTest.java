package com.wrf.backend.controller;
import com.wrf.backend.model.request.LoginModel;
import com.wrf.backend.model.request.UserRegistrationModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest extends BaseTest {

    @Test
    public void addUserTest() throws Exception {
        var model = new UserRegistrationModel(
                "Илья", "Есовеев",
                "79828765588", "1234567");

        post("users", model, isOk);
    }

    @Test
    public void failAddUserTest() throws Exception {
        var model = new UserRegistrationModel(
                "Илья", null,
                "7982876558", "1234567");

        post("users", model, isBadRequest);
    }

    @Test
    public void loginTest() throws Exception {
        var model = new LoginModel("79828156297", "123456");
        post("auth/login", model, isOk);
    }

    @Test
    public void failLoginTest() throws Exception {
        var model = new LoginModel("7982815678", "1234567");
        post("auth/login", model, isBadRequest);
    }


}
