package xyz.digivice.drivemerge.googledrive.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

@Component
public class GoogleDriveAuth {

    @Value("${google.api.CLIENT_ID}")
    private String clientId;

    @Value("${google.api.REDIRECT_URI}")
    private String redirectUri;

    @Value("${google.api.DRIVE_SCOPE}")
    private String driveScope;

    @Value("${google.api.AUTHORIZATION_SERVER_ENCODED_URL}")
    private String authEncodedUrl;

    @Value("${google.api.CLIENT_SECRET_PATH}")
    private String clientSecretPath;

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getDriveScope() {
        return driveScope;
    }

    public String getAuthEncodedUrl() {
        return authEncodedUrl;
    }

    public String getClientSecretPath() {
        return clientSecretPath;
    }




    public  String getAuthorizationUrl() throws IOException {
        return new GoogleAuthorizationCodeRequestUrl(
                getAuthEncodedUrl(),
                getClientId(),
                getRedirectUri(),
                Collections.singleton(getDriveScope())
        ).build();
    }

    public String getClientSecrets() throws IOException {
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        try (InputStreamReader reader = new InputStreamReader(
                GoogleDriveAuth.class.getResourceAsStream(getClientSecretPath()))) {
            return GoogleClientSecrets.load(jsonFactory, reader).toPrettyString();
        }
    }
}
