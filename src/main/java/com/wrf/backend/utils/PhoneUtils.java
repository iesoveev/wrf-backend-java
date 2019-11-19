package com.wrf.backend.utils;

import java.util.regex.Pattern;

class PhoneUtils {

    /**
     *  Начинается на 7. После 7-ки имеет длину 10 символов
     */
    private static final String PHONE_PATTERN = "^((7)([0-9]{10}))$";
    private static final Pattern pattern = Pattern.compile(PHONE_PATTERN);

    private PhoneUtils() {}

    static boolean isPhoneMatchesPattern(String phone) {
        return pattern.matcher(phone).matches();
    }
}
