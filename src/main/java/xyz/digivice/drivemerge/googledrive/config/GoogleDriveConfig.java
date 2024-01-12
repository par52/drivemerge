package xyz.digivice.drivemerge.googledrive.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.digivice.drivemerge.application.AppProperties;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class GoogleDriveConfig {

    @Autowired
    private AppProperties appProperties;

    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    @Bean
    public Drive drive() throws IOException, GeneralSecurityException {
        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,

                // Your HttpRequestInitializer implementation, or null if not needed
                null
        )
                .setApplicationName(appProperties.getApplicationName())
                .build();
    }
}

