package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventIntermediate {

    private String id;

    private Date create_at;

    private String text;

    private String username;
}
