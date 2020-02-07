package com.wrf.backend.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AndroidLogDTO {

    private String message;

    private String createdTime;
}
