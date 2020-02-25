package com.wrf.backend.service;

import com.wrf.backend.config.AppConfig;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.wrf.backend.exception.ErrorMessage.*;

@Service
public class ImageService {

    private static final Logger log = LogManager.getLogger(ImageService.class);

    private static final Map<String, String> unsavedImages = new ConcurrentHashMap<>();

    final AppConfig appConfig;

    public ImageService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Async("imageExecutor")
    void saveImage(final String image, final String pathValue, final String imageUuid) {
        final byte[] imageValue = Base64.decodeBase64(image);
        final var path = Paths.get(pathValue);

        try {
            Files.write(path, imageValue, StandardOpenOption.CREATE);
        } catch(IOException e) {
            //todo передалать на redis как слезем с heroku
            unsavedImages.put(imageUuid, image);
            log.warn(FILE_IS_NOT_WRITTEN + ": " + e);
        }
    }

    void reSaveImage(final String image, final String pathValue, final String imageUuid) {
        final byte[] imageValue = Base64.decodeBase64(image);
        final var path = Paths.get(pathValue);

        try {
            Files.write(path, imageValue, StandardOpenOption.CREATE);
            unsavedImages.remove(imageUuid);
        } catch(IOException e) {
            log.warn(e);
            throw new BusinessException(FILE_IS_NOT_WRITTEN);
        }
    }

    byte[] getImage(final String pathValue) {
        final var path = Paths.get(pathValue);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            log.warn(e);
            throw new BusinessException(FILE_IS_NOT_READ);
        }
    }

    @Async("imageExecutor")
    void replaceImage(final String targetImageUuid, final String newImageUuid,
                      String newImage, String imagePath) {
        final var newImagePath = Paths.get(imagePath + newImageUuid);
        final var targetFile = new File(imagePath + targetImageUuid);
        final byte[] newImageValue = Base64.decodeBase64(newImage);

        if (targetFile.delete()) {
            try {
                Files.write(newImagePath, newImageValue, StandardOpenOption.CREATE);
            } catch (IOException e) {
                // todo переделать на redis
                unsavedImages.put(newImageUuid, newImage);
                log.warn(FILE_IS_NOT_WRITTEN.getMessage() + ": " + e);
            }
        } else {
            // todo переделать на redis
            unsavedImages.put(newImageUuid, newImage);
            log.warn(FILE_IS_NOT_DELETED.getMessage());
        }
    }

    void checkImageState(final String imageUuid, final String imagePath) {
        if (unsavedImages.containsKey(imageUuid)) {
            reSaveImage(unsavedImages.get(imageUuid),
                    imagePath + imageUuid, imageUuid);
        }
    }
}
