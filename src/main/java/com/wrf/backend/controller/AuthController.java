package com.wrf.backend.controller;

import com.wrf.backend.model.request.LoginModel;
import com.wrf.backend.model.request.UserRegistrationModel;
import com.wrf.backend.model.response.TokenDTO;
import com.wrf.backend.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "Регистрация")
    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO addUser(@Valid @RequestBody UserRegistrationModel model,
                            @RequestHeader String deviceToken) throws NoSuchAlgorithmException {
        // todo придумать что делать, если токен будет не валидным и как его проверить
        return authService.addUser(model, deviceToken);
    }

    @ApiOperation(value = "Авторизация")
    @PostMapping("/auth/login")
    public TokenDTO login(@Valid @RequestBody LoginModel model) throws NoSuchAlgorithmException {
        return authService.login(model);
    }
}
