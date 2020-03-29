package com.wrf.backend.controller;

import com.wrf.backend.model.response.UserDTO;
import com.wrf.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Получить список всех работников")
    @GetMapping
    public List<UserDTO> employees() {
        return userService.findAllUsers();
    }
}
