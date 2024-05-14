package com.enigmacamp.reservationcampus.service.impl;

import com.enigmacamp.reservationcampus.config.FileStorageProperties;
import com.enigmacamp.reservationcampus.service.ImageStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public ImageStorageServiceImpl(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadImage())
                .toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e){
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Override
    public String storeFile(MultipartFile picture) {
        String fileName = picture.getOriginalFilename();
        assert fileName != null;
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try{
            Files.copy(picture.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (Exception e) {
            throw new RuntimeException("Erorr occurred while storing file " + fileName, e);
        }
    }
}
