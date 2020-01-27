package com.wrf.backend.utils;

import com.wrf.backend.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String dateTimePattern = "dd.MM.yyyy HH:mm:ss";
    public static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(dateTimePattern);

    public static Date format(Date date) {
        String dateString = dateTimeFormatter.format(date);
        try {
            return dateTimeFormatter.parse(dateString);
        } catch (ParseException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private DateUtils() {
    }
}