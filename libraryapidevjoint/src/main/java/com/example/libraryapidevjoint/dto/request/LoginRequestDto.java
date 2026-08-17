package com.example.libraryapidevjoint.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Request DTO for user login")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @Schema(description = "Email address", example = "john.doe@example.com")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid Email")
    private String email;

    @Schema(description = "Account password", example = "secret123")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
