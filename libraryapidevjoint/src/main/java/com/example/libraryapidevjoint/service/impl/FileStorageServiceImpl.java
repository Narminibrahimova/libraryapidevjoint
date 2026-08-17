package com.example.libraryapidevjoint.service.impl;

import com.example.libraryapidevjoint.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path uploadDirectory =
            Paths.get("uploads").toAbsolutePath().normalize();
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    @Override
    public String upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                    "File size must not exceed 5 MB");
        }

        String contentType = file.getContentType();
        if (contentType == null ||
                !contentType.equals("application/pdf")) {
            throw new IllegalArgumentException(
                    "Only PDF files are allowed");
        }
        Files.createDirectories(uploadDirectory);
        String fileName =
                StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation =
                uploadDirectory.resolve(fileName).normalize();
        if (!targetLocation.startsWith(uploadDirectory)) {
            throw new IllegalArgumentException("Invalid file name");
        }
        Files.copy(file.getInputStream(),
                targetLocation,
                java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
    @Override
    public byte[] download(String fileName) throws IOException {
        Path filePath =
                uploadDirectory.resolve(fileName).normalize();
        if (!filePath.startsWith(uploadDirectory)) {
            throw new IllegalArgumentException("Invalid file name");
        }
        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException("File not found");
        }
        return Files.readAllBytes(filePath);
    }
}
