package com.wrf.backend.utils;

import com.wrf.backend.exception.ErrorCode;
import com.wrf.backend.exception.RestException;
import com.wrf.backend.model.response.UserToken;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class TokenUtils {

    private static final Integer tokenExpirationDays = 1;

    private TokenUtils() {
    }

    public static void validate(UserToken userToken) {
        if (userToken == null || userToken.getExpirationTime().before(new Date())) {
            throw new RestException(ErrorCode.ACCESS_DENIED.getCode(), "Необходима авторизация");
        }
    }

    public static Date getTokenExpirationTime() {
        return DateUtils.addDays(new Date(), tokenExpirationDays);
    }

}
