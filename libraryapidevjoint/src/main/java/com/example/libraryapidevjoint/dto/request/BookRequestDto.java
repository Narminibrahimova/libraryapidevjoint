package com.example.libraryapidevjoint.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(description = "Request DTO for creating or updating a book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    @Schema(description = "Title of the book", example = "The Great Gatsby")
    @NotBlank(message = "Title cannot be empty")
    @Size(min=2,max=100,message = "Title must be between 2 and 100 characters")
    String title;

    @Schema(description = "Price of the book", example = "29.99")
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be grater than 0")
    double price;

    @Schema(description = "ID of the book author", example = "1")
    @NotNull(message = "Author id cannot be null")
    Long authorId;

    @Schema(description = "List of category IDs associated with the book", example = "[1, 2]")
    @NotEmpty(message = "At least one category must be selected")
    List<Long> categoryIds;
}
