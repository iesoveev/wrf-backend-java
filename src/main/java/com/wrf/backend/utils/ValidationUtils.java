package com.wrf.backend.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wrf.backend.exception.BusinessException;
import io.swagger.annotations.ApiModelProperty;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidationUtils {

    private ValidationUtils() {}

    public static void validate(Object model) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();

        for (Field field : model.getClass().getDeclaredFields()) {
            String fullName = field.getAnnotation(ApiModelProperty.class).value();
            if (!field.isAccessible())
                field.setAccessible(true);

            Object property = field.get(model);

            if (field.getAnnotation(ApiModelProperty.class).required() && property == null)
                errors.add("Отсутствует обязательный параметр " + fullName);

            if (field.getName().equals("phone") &&
                    property instanceof String &&
                    !PhoneUtils.isPhoneMatchesPattern((String) property))
                errors.add("Некорректный формат телефона");

            if (!errors.isEmpty())
                throw new BusinessException(String.join(", ", errors));
        }
    }
}
