package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushModel {

    private String topic;

    private String title;

    private String message;

    public PushModel(String topic, String title) {
        this.topic = topic;
        this.title = title;
    }
}
