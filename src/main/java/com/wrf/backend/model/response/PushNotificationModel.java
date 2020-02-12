package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public final class PushNotificationModel {

    private final String topic;

    private final String title;

    @Setter
    private String message;
}
