package com.example.libraryapidevjoint.service.impl;

import com.example.libraryapidevjoint.dto.request.BorrowBookRequestDto;
import com.example.libraryapidevjoint.dto.response.BorrowRecordResponseDto;
import com.example.libraryapidevjoint.entity.AppUser;
import com.example.libraryapidevjoint.entity.Book;
import com.example.libraryapidevjoint.entity.BorrowRecord;
import com.example.libraryapidevjoint.entity.BorrowStatus;
import com.example.libraryapidevjoint.exception.BookAlreadyBorrowedException;
import com.example.libraryapidevjoint.exception.ResourceNotFoundException;
import com.example.libraryapidevjoint.mapper.BorrowMapper;
import com.example.libraryapidevjoint.repository.BookRepository;
import com.example.libraryapidevjoint.repository.BorrowRecordRepository;
import com.example.libraryapidevjoint.repository.UserRepository;
import com.example.libraryapidevjoint.service.BorrowService;
import com.example.libraryapidevjoint.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowMapper borrowMapper;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public BorrowRecordResponseDto borrowBook(BorrowBookRequestDto request) {
        AppUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));
        borrowRecordRepository
                .findByBookIdAndStatus(
                        book.getId(),
                        BorrowStatus.BORROWED
                )
                .ifPresent(record -> {
                    throw new BookAlreadyBorrowedException(
                            "Book is already borrowed"
                    );
                });
        BorrowRecord borrowRecord = BorrowRecord.builder()
                .book(book)
                .user(user)
                .borrowDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .status(BorrowStatus.BORROWED)
                .build();
        BorrowRecord savedBorrowRecord =
                borrowRecordRepository.save(borrowRecord);
        notificationService.sendBorrowNotification(
                user.getEmail(),
                book.getTitle()
        );
        return borrowMapper.toDto(savedBorrowRecord);
    }

    @Override
    @Transactional
    public BorrowRecordResponseDto returnBook(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Borrow record not found"));
        if (borrowRecord.getStatus() == BorrowStatus.RETURNED) {
            throw new IllegalArgumentException("Book is already returned");
        }
        borrowRecord.setStatus(BorrowStatus.RETURNED);
        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(borrowRecord);
        throw new RuntimeException("Rollback test");
    }


}
