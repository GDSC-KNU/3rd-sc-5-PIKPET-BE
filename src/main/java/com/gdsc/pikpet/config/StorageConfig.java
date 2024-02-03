package com.gdsc.pikpet.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    // Google Cloud Storage json 경로
    @Value("${gcs.json.path}")
    private String jsonPath;

    @Bean
    public Storage googleStorage() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        return storage;
    }
}
