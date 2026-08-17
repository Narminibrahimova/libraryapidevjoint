package com.example.libraryapidevjoint.dto.response;

import com.example.libraryapidevjoint.entity.BorrowStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Schema(description = "Response DTO containing borrow record details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowRecordResponseDto {
     @Schema(description = "Borrow record ID", example = "1")
     Long id;

     @Schema(description = "ID of the borrowed book", example = "1")
     Long bookId;

     @Schema(description = "ID of the borrowing user", example = "1")
     Long userId;

     @Schema(description = "Date when the book was borrowed", example = "2026-08-17")
     LocalDate borrowDate;

     @Schema(description = "Due date for returning the book", example = "2026-08-31")
     LocalDate dueDate;

     @Schema(description = "Actual return date", example = "2026-08-25")
     LocalDate returnDate;

     @Schema(description = "Current status of the borrow record", example = "BORROWED")
     BorrowStatus status;
}
