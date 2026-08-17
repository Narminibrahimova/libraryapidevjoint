package com.example.libraryapidevjoint.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Response DTO containing book details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {
    @Schema(description = "Unique ID of the book", example = "1")
    private Long id;

    @Schema(description = "Title of the book", example = "The Great Gatsby")
    private String title;

    @Schema(description = "Price of the book", example = "29.99")
    private Double price;

    @Schema(description = "Full name of the author", example = "F. Scott Fitzgerald")
    private String authorName;
}
