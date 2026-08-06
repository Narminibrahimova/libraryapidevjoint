package com.example.libraryapidevjoint.service;

import com.example.libraryapidevjoint.dto.request.BorrowBookRequestDto;
import com.example.libraryapidevjoint.dto.response.BorrowRecordResponseDto;

public interface BorrowService {
    BorrowRecordResponseDto borrowBook(BorrowBookRequestDto request);
    BorrowRecordResponseDto returnBook(Long borrowRecordId);
}
