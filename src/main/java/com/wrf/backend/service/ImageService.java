package com.wrf.backend.service;

import com.wrf.backend.db_api.UserDbApi;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.response.PushEvent;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

import static com.wrf.backend.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static final Logger log = LogManager.getLogger(ImageService.class);

    private final FcmService fcmService;

    private final AuthService authService;

    private final UserDbApi userDbApi;

    @Async("imageExecutor")
    void saveImage(final String image, final String pathValue, final String imageUuid) {
        final byte[] imageValue = Base64.decodeBase64(image);
        final var path = Paths.get(pathValue);

        try {
            Files.write(path, imageValue, StandardOpenOption.CREATE);
        } catch (IOException e) {
            log.warn(FILE_IS_NOT_WRITTEN + ": " + e);
            final var user = userDbApi.findById(authService.getUserInfo().getId());
            fcmService.buildAndSendPush(Collections.singletonList(user.getDeviceToken()), null, PushEvent.SAVE_IMAGE, null);
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
}
