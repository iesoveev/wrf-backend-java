package com.wrf.backend.utils;

import com.wrf.backend.exception.BusinessException;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String dateTimePattern = "dd.mm.yyyy, HH:mm";
    public static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(dateTimePattern);

    public static Date parse(@Nullable final String date) {
        try {
            return dateTimeFormatter.parse(date);
        } catch (ParseException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private DateUtils() {
    }
}