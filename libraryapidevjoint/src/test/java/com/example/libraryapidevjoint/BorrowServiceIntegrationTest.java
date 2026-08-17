package com.example.libraryapidevjoint;

import com.example.libraryapidevjoint.dto.request.BorrowBookRequestDto;
import com.example.libraryapidevjoint.entity.BorrowRecord;
import com.example.libraryapidevjoint.entity.BorrowStatus;
import com.example.libraryapidevjoint.repository.BorrowRecordRepository;
import com.example.libraryapidevjoint.service.BorrowService;
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
    void returnBook_ShouldMarkBookAsReturned() {
        BorrowBookRequestDto request = new BorrowBookRequestDto();
        request.setBookId(1L);
        borrowService.borrowBook(request, 1L);
        BorrowRecord record = borrowRecordRepository.findAll().getFirst();
        borrowService.returnBook(record.getId());
        BorrowRecord dbRecord = borrowRecordRepository
                .findById(record.getId())
                .orElseThrow();
        assertEquals(BorrowStatus.RETURNED, dbRecord.getStatus());
        assertNotNull(dbRecord.getReturnDate());
    }
}
