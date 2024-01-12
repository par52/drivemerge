package xyz.digivice.drivemerge.googledrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.digivice.drivemerge.googledrive.service.GoogleDriveService;

import java.io.IOException;


//@RequestMapping("/api/v1/drive")
@Controller
public class DriveController {

    @Autowired
    private GoogleDriveService googleDriveService;


    @GetMapping("/drive/upload")
    public String showUpload(){
        return "google/drive/upload";
    }

    @PostMapping("/drive/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileId = googleDriveService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully. File ID: " + fileId);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }
}
