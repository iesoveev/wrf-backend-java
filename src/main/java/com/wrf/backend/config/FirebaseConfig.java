package com.wrf.backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FirebaseConfig {

    final AppConfig appConfig;

    public FirebaseConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @PostConstruct
    void initialize() throws IOException {
        final Path p = Paths.get(appConfig.getFirebaseAccountFile());
        try (InputStream serviceAccount = Files.newInputStream(p)) {
            final FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        }
    }
}
