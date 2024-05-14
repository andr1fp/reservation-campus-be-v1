package com.enigmacamp.reservationcampus.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    public String storeFile(MultipartFile picture);
}
