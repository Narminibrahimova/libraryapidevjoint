package com.example.libraryapidevjoint.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Request DTO for borrowing a book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBookRequestDto {
    @Schema(description = "ID of the book to borrow", example = "1")
    @NotNull(message = "Book id cannot be null")
    private Long bookId;
}
