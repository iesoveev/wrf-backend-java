package com.wrf.backend.service;

import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.ErrorMessage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class ImageService {

    void saveImage(final String image, final String pathValue) {
        byte[] imageValue = Base64.decodeBase64(image);
        var path = Paths.get(pathValue);

        try {
            Files.write(path, imageValue, StandardOpenOption.CREATE);
        } catch(IOException e) {
            throw new BusinessException(ErrorMessage.FILE_IS_NOT_WRITTEN);
        }
    }
}
