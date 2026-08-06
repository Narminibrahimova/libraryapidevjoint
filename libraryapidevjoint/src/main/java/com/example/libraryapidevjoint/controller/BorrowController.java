package com.example.libraryapidevjoint.controller;

import com.example.libraryapidevjoint.dto.request.BorrowBookRequestDto;
import com.example.libraryapidevjoint.dto.response.BorrowRecordResponseDto;
import com.example.libraryapidevjoint.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<BorrowRecordResponseDto> borrowBook(
            @RequestBody BorrowBookRequestDto request) {
        BorrowRecordResponseDto response =
                borrowService.borrowBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @PutMapping("/return/{borrowRecordId}")
    public BorrowRecordResponseDto returnBook(
            @PathVariable Long borrowRecordId) {
        return borrowService.returnBook(borrowRecordId);
    }
}
