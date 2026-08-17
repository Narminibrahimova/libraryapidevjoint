package com.example.libraryapidevjoint.controller;

import com.example.libraryapidevjoint.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Upload PDF file",
            description = "Uploads a PDF file with a maximum size of 5 MB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "File uploaded successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid file type, empty file or file exceeds 5 MB"
            )
    })
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





    @Operation(
            summary = "Download PDF file",
            description = "Downloads an uploaded PDF file by its name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "File downloaded successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid file name"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "File not found"
            )
    })
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
