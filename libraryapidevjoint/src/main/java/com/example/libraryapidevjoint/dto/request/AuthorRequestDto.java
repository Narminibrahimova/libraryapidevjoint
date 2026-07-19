package com.example.libraryapidevjoint.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {
    @NotBlank(message = "Full name cannot be empty")
    @Size(min=2,max=100,message = "Full name must be between 2 and 100 characters")
    private String fullName;
}
