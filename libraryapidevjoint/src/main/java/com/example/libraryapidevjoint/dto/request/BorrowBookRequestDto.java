package com.example.libraryapidevjoint.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBookRequestDto {
    @NotNull(message = "Book id cannot be null")
    private Long bookId;
}
