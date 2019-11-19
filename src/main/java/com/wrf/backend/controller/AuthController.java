package com.wrf.backend.controller;

import com.wrf.backend.model.request.UserRegistrationModel;
import com.wrf.backend.model.response.TokenModel;
import com.wrf.backend.service.AuthService;
import com.wrf.backend.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public TokenModel addUser(@RequestBody UserRegistrationModel model) throws IllegalAccessException, NoSuchAlgorithmException {
        ValidationUtils.validate(model);
        return authService.addUser(model);
    }

    @PostMapping("/auth/login")
    @Transactional
    public TokenModel login(@RequestBody UserRegistrationModel model) throws IllegalAccessException, NoSuchAlgorithmException {
        ValidationUtils.validate(model);
        return authService.login(model);
    }
}
