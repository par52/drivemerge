package xyz.digivice.drivemerge.home.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.digivice.drivemerge.application.AppProperties;
import xyz.digivice.drivemerge.googledrive.auth.GoogleDriveAuth;
import xyz.digivice.drivemerge.utils.UrlUtils;

@Controller
public class HomeController {

    @Autowired
    AppProperties appProperties;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest, Model model){
        model.addAttribute("appName", appProperties.getApplicationName());
        String googleAuthRedirectUrl = new UrlUtils(httpServletRequest).getFullUrl() + "/" + new GoogleDriveAuth().getRedirectUri();
        return "home/index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "home/blog";
    }
}


