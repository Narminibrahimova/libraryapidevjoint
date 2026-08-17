package com.example.libraryapidevjoint.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Response DTO containing author details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDto {
    @Schema(description = "Unique ID of the author", example = "1")
    private Long id;

    @Schema(description = "Full name of the author", example = "George Orwell")
    private String fullName;
}
