package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PushEvent {

    NEW_EVENT("Новое событие"),
    WS_OPEN("Открытие смены"),
    WS_CLOSE("Закрытие смены"),
    SAVE_IMAGE("Сохранение картинки");

    @Getter
    private final String description;


}
