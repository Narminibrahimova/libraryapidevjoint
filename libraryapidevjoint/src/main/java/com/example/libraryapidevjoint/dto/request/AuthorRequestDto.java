package com.example.libraryapidevjoint.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Request DTO for creating or updating an author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {
    @Schema(description = "Full name of the author", example = "George Orwell")
    @NotBlank(message = "Full name cannot be empty")
    @Size(min=2,max=100,message = "Full name must be between 2 and 100 characters")
    private String fullName;
}
