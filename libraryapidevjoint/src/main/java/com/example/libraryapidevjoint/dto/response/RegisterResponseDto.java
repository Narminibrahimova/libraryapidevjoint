package com.example.libraryapidevjoint.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {
    private Long id;
    private String fullName;
    private String email;
}
