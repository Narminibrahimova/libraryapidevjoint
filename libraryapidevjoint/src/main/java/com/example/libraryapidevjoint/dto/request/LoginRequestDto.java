package com.example.libraryapidevjoint.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
