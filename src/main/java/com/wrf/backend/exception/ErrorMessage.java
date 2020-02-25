package com.wrf.backend.exception;

import java.text.MessageFormat;

public enum ErrorMessage {

    FILE_IS_NOT_WRITTEN("Ошибка записи файла"),
    FILE_IS_NOT_READ("Ошибка чтения файла"),
    FILE_IS_NOT_DELETED("Ошибка удаления файла"),
    INNOVATION_IS_NOT_FOUND("Категория не найдена"),
    INTERNAL("Внутренняя ошибка сервера"),
    INVALID_LOGIN_DATA("Неверный логин или пароль"),
    NO_AUTHORIZATION("Доступ запрещен"),
    USER_IS_ALREADY_REGISTERED("Пользователь c телефоном {0} уже зарегестрирован"),
    USER_IS_NOT_FOUND("Пользователь не найден"),
    SHIFT_IS_NOT_FOUND("Смена не найдена"),
    SHIFT_IS_ALREADY_CLOSED("Смена уже закрыта"),
    USER_IS_ALREADY_ON_THE_SHIFT("Пользователь уже состоит в смене"),
    ONBOARDING_IS_NOT_FOUND("Онбоардинг не найден"),
    ONBOARDING_IS_ALREADY_EXISTED("Онбоардинг с таким типом уже существует");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String format(String value) {
        return MessageFormat.format(message, value);
    }
}
