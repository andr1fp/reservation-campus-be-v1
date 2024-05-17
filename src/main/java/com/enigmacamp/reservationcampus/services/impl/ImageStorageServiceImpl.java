package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.config.FileStorageProperties;
import com.enigmacamp.reservationcampus.services.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

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
    public String storeFile(MultipartFile picture, String id_profile) {
        String fileName = picture.getOriginalFilename();
        String[] splitName = fileName.split("\\.");
        fileName = splitName[0] + "_" + id_profile + "." + splitName[splitName.length - 1];

        assert fileName != null;

        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try{
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            if (Files.exists(targetLocation)) {
                Files.delete(targetLocation);
            }
            Files.copy(picture.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (Exception e) {
            throw new RuntimeException("Erorr occurred while storing file " + fileName, e);
        }
    }
}
