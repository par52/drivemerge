package xyz.digivice.drivemerge.googledrive.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;


public class GoogleDriveAuthCallback {

    public static void main(String[] args) throws IOException {
        String authorizationCode = "";

        // Exchange authorization code for tokens
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                "https://www.googleapis.com/oauth2/v4/token",
                new GoogleDriveAuth().getClientId(),
                new GoogleDriveAuth().getRedirectUri(),
                authorizationCode,
                new GoogleDriveAuth().getRedirectUri()
        ).execute();

        // Print the access token and refresh token
        System.out.println("Access Token: " + tokenResponse.getAccessToken());
        System.out.println("Refresh Token: " + tokenResponse.getRefreshToken());
    }
}

