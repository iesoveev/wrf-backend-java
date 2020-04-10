package com.wrf.backend.service;

import com.wrf.backend.db_api.UserDbApi;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.response.PushEvent;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import static com.wrf.backend.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static final Logger log = LogManager.getLogger(ImageService.class);

    private final FcmService fcmService;

    private final AuthService authService;

    private final UserDbApi userDbApi;

    private final MinioClient minioClient;

    @Async("imageExecutor")
    void saveImage(final String image, final String bucket, final String imageUuid) {
        try {
            if (!minioClient.bucketExists(bucket)) {
                minioClient.makeBucket(bucket);
            }
            ByteArrayInputStream in = new ByteArrayInputStream(Base64.decodeBase64(image));
            minioClient.putObject(bucket, imageUuid, in,
                    null, null, null, null);
        } catch (Exception ex) {
            log.warn(FILE_IS_NOT_WRITTEN + ": " + ex);
            final var user = userDbApi.findById(authService.getUserInfo().getId());
            fcmService.buildAndSendPush(Collections.singletonList(user.getDeviceToken()), null, PushEvent.SAVE_IMAGE, null);
        }
    }

    byte[] getImage(final String bucket, final String imageUuid) {
        try {
            return minioClient.getObject(bucket, imageUuid).readAllBytes();
        } catch (Exception e) {
            log.warn(e);
            throw new BusinessException(FILE_IS_NOT_READ);
        }
    }
}
