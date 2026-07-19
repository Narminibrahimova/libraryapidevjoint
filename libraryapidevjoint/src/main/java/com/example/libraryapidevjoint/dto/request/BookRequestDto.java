package com.example.libraryapidevjoint.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    @NotBlank(message = "Title cannot be empty")
    @Size(min=2,max=100,message = "Title must be between 2 and 100 characters")
    String title;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be grater than 0")
    double price;
    @NotNull(message = "Author id cannot be null")
    Long authorId;
}
