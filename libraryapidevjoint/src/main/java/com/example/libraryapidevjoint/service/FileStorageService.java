package com.example.libraryapidevjoint.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    String upload(MultipartFile file) throws IOException;
    byte[] download(String fileName) throws IOException;
}
