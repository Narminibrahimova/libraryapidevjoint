package com.example.libraryapidevjoint.controller;

import com.example.libraryapidevjoint.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(
            @RequestParam("file") MultipartFile file)
            throws IOException {
        String fileName = fileStorageService.upload(file);
        return ResponseEntity.ok(
                Map.of(
                        "message", "File uploaded successfully",
                        "fileName", fileName
                )
        );
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(
            @PathVariable String fileName)
            throws IOException {
        byte[] file = fileStorageService.download(fileName);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\""
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }
}
