package com.wrf.backend.utils;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    private PasswordUtils() {
    }

    public static String getPasswordHash(String password) throws NoSuchAlgorithmException {
        return DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5")
                        .digest(password.getBytes(StandardCharsets.UTF_8)));
    }
}
