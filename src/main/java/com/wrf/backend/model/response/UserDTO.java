package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseDTO {

    private String phone;

    private String fullName;
}
