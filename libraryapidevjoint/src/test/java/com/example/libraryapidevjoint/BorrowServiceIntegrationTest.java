package com.example.libraryapidevjoint;

import com.example.libraryapidevjoint.dto.request.BorrowBookRequestDto;
import com.example.libraryapidevjoint.entity.AppUser;
import com.example.libraryapidevjoint.entity.Book;
import com.example.libraryapidevjoint.entity.BorrowRecord;
import com.example.libraryapidevjoint.entity.BorrowStatus;
import com.example.libraryapidevjoint.exception.BookAlreadyBorrowedException;
import com.example.libraryapidevjoint.repository.BookRepository;
import com.example.libraryapidevjoint.repository.BorrowRecordRepository;
import com.example.libraryapidevjoint.repository.UserRepository;
import com.example.libraryapidevjoint.service.BorrowService;
import com.example.libraryapidevjoint.service.impl.BorrowServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BorrowServiceIntegrationTest {
    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;


    @Test
    void returnBook_ShouldRollbackTransaction() {
        BorrowBookRequestDto request = new BorrowBookRequestDto();
        request.setUserId(1L);
        request.setBookId(1L);
        borrowService.borrowBook(request);
        BorrowRecord record = borrowRecordRepository.findAll().getFirst();
        assertThrows(RuntimeException.class,
                () -> borrowService.returnBook(record.getId()));
        BorrowRecord dbRecord = borrowRecordRepository
                .findById(record.getId())
                .orElseThrow();
        assertEquals(BorrowStatus.BORROWED, dbRecord.getStatus());
        assertNull(dbRecord.getReturnDate());
    }
}
