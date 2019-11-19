package com.wrf.backend.service;

import com.wrf.backend.exception.ErrorCode;
import com.wrf.backend.exception.RestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class ImageService {

    private static final Logger LOG = LogManager.getLogger(ImageService.class);

    void saveImage(String image, String pathValue) {
        byte[] imageValue = Base64.decodeBase64(image);
        Path path = Paths.get(pathValue);

        try {
            Files.write(path, imageValue, StandardOpenOption.CREATE);
        } catch(IOException e) {
            LOG.error("Can`t write image", e);
            throw new RestException(ErrorCode.UPLOAD_FILE_EXCEPTION.getCode(), "Не удалось сохранить файл ");
        }
    }
}
