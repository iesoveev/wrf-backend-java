package com.wrf.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${onboarding.image.path}")
    private String onboardingImagePath;

    @Value("${innovation.image.path}")
    private String innovationImagePath;

    @Value("${firebase.account.file}")
    private String firebaseAccountFile;
}
