package com.example.libraryapidevjoint.dto.response;

import com.example.libraryapidevjoint.entity.BorrowStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowRecordResponseDto {
     Long id;
     Long bookId;
     Long userId;
     LocalDate borrowDate;
     LocalDate dueDate;
     LocalDate returnDate;
     BorrowStatus status;
}
