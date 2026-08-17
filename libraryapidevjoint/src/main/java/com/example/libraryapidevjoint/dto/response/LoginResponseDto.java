package com.example.libraryapidevjoint.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Response DTO containing JWT authentication token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {
    @Schema(description = "JWT Bearer Access Token", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
}
