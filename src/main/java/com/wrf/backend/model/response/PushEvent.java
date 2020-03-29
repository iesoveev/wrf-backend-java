package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PushEvent {

    NEW_EVENT("Новое событие"),
    WS_CLOSE("Закрытие смены");

    @Getter
    private final String description;


}
