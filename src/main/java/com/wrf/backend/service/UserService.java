package com.wrf.backend.service;

import com.wrf.backend.db_api.UserDbApi;
import com.wrf.backend.model.response.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDbApi userDbApi;

    public List<UserDTO> findAllUsers() {
        return userDbApi.findAllUsers();
    }
}
