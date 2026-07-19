package com.example.libraryapidevjoint.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String title;
    private Double price;
    private String authorName;
}
