package com.wrf.backend.controller;

import com.wrf.backend.model.request.LoginModel;
import com.wrf.backend.model.request.UserRegistrationModel;
import com.wrf.backend.model.response.TokenDTO;
import com.wrf.backend.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Api(tags = "auth")
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "Регистрация")
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO addUser(@Valid @RequestBody UserRegistrationModel model,
                            @RequestHeader String deviceToken) throws NoSuchAlgorithmException {
        return authService.addUser(model, deviceToken);
    }

    @ApiOperation(value = "Авторизация")
    @PostMapping("/auth/login")
    public TokenDTO login(@Valid @RequestBody LoginModel model) throws NoSuchAlgorithmException {
        return authService.login(model);
    }
}
