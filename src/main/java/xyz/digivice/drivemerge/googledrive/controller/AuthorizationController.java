package xyz.digivice.drivemerge.googledrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import xyz.digivice.drivemerge.googledrive.auth.GoogleDriveAuth;

import java.io.IOException;

@Controller
public class AuthorizationController {

    private final GoogleDriveAuth googleDriveAuth;

    @Autowired
    public AuthorizationController(GoogleDriveAuth googleDriveAuth) {
        this.googleDriveAuth = googleDriveAuth;
    }

    @GetMapping("/authorize")
    public RedirectView authorize() {
        try {
            String authorizationUrl = googleDriveAuth.getAuthorizationUrl();
            System.out.println("authorizationUrl=" + authorizationUrl);
            return new RedirectView(authorizationUrl);
        } catch (IOException e) {
            return new RedirectView("/error");
        }
    }

    // Other controller methods...
}
