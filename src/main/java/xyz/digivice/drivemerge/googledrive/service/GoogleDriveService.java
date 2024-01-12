package xyz.digivice.drivemerge.googledrive.service;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class GoogleDriveService {

    @Autowired
    private Drive drive;

    public String uploadFile(MultipartFile file) throws IOException {
        // Convert MultipartFile to java.io.File
        java.io.File convertedFile = convertMultiPartToFile(file);

        // Continue with your existing logic...
        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());

        FileContent mediaContent = new FileContent("image/jpeg", convertedFile);

        File uploadedFile = drive.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();

        return uploadedFile.getId();
    }

    private java.io.File convertMultiPartToFile(MultipartFile file) throws IOException {
        java.io.File convertedFile = new java.io.File(file.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(convertedFile)) {
            os.write(file.getBytes());
        }
        return convertedFile;
    }
}

