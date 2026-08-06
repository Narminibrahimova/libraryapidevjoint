package com.example.libraryapidevjoint.repository;

import com.example.libraryapidevjoint.entity.BorrowRecord;
import com.example.libraryapidevjoint.entity.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findByBookIdAndStatus(
            Long bookId,
            BorrowStatus status
    );
}
