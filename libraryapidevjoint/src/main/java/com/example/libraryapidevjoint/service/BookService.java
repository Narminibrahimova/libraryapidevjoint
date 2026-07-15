package com.example.libraryapidevjoint.service;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto create(BookRequestDto bookRequestDto);
    List<BookResponseDto> getAll();
    BookResponseDto getById(Long id);
    BookResponseDto update(Long id, BookRequestDto bookRequestDto);
    void delete(Long id);
}
