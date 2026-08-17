package com.example.libraryapidevjoint.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Response DTO after registration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {
    @Schema(description = "User ID", example = "1")
    private Long id;

    @Schema(description = "Full name", example = "John Doe")
    private String fullName;

    @Schema(description = "Email address", example = "john.doe@example.com")
    private String email;
}
