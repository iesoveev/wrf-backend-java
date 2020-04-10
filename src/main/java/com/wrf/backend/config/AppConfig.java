package com.wrf.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {

    @Value("${minio.innovation.bucket}")
    private String innovationBucket;

    @Value("${minio.onboarding.bucket}")
    private String onboardingBucket;
}
