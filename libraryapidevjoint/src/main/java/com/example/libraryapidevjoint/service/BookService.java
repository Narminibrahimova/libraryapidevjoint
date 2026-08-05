package com.example.libraryapidevjoint.service;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    BookResponseDto create(BookRequestDto bookRequestDto);
    Page<BookResponseDto> getAll(int page, int size,String sortBy);
    BookResponseDto getById(Long id);
    BookResponseDto update(Long id, BookRequestDto bookRequestDto);
    void delete(Long id);

    List<BookResponseDto> filterBooks(
            String title,
            String author,
            String category,
            Double minPrice
    );
    List<BookResponseDto> searchBooks(
            String title,
            String author,
            String category,
            Double minPrice
    );
}
