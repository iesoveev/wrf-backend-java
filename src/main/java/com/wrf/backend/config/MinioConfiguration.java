package com.wrf.backend.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class MinioConfiguration {

    @Value("${minio.server}")
    private String minioServer;

    @Value("${minio.access.key}")
    private String minioAccessKey;

    @Value("${minio.secret.key}")
    private String minioSecretKey;

    @Bean
    MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(minioServer, minioAccessKey, minioSecretKey);
    }
}
