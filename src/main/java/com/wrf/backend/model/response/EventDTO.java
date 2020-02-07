package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventDTO extends BaseDTO {

    private String text;

    private String username;

    private Date createdDateTime;

    private String createdTime;
}
