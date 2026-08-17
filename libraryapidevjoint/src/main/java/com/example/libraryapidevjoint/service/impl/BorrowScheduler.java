package com.example.libraryapidevjoint.service.impl;

import com.example.libraryapidevjoint.entity.BorrowRecord;
import com.example.libraryapidevjoint.entity.BorrowStatus;
import com.example.libraryapidevjoint.repository.BorrowRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BorrowScheduler {
    private final BorrowRecordRepository borrowRecordRepository;
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void markOverdueBooks() {
        List<BorrowRecord> records =
                borrowRecordRepository.findByStatusAndDueDateBefore(
                        BorrowStatus.BORROWED,
                        LocalDate.now()
                );
        for (BorrowRecord record : records) {
            record.setStatus(BorrowStatus.OVERDUE);
        }
        borrowRecordRepository.saveAll(records);
    }
}
