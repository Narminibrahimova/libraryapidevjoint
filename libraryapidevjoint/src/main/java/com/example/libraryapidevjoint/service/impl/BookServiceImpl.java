package com.example.libraryapidevjoint.service.impl;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;
import com.example.libraryapidevjoint.entity.Author;
import com.example.libraryapidevjoint.entity.Book;
import com.example.libraryapidevjoint.repository.AuthorRepository;
import com.example.libraryapidevjoint.repository.BookRepository;
import com.example.libraryapidevjoint.service.BookService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BookResponseDto create(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Book book =Book.builder()
                .title(bookRequestDto.getTitle())
                .price(bookRequestDto.getPrice())
                .author(author)
                .build();
        Book savedBook = bookRepository.save(book);
        return BookResponseDto.builder()
                .id(savedBook.getId())
                .title(savedBook.getTitle())
                .price(savedBook.getPrice())
                .authorName(savedBook.getAuthor().getFullName())
                .build();
    }

    @Override
    public List<BookResponseDto> getAll() {
        return bookRepository.findAll().stream()
                .map(book -> BookResponseDto.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .price(book.getPrice())
                        .authorName(book.getAuthor().getFullName())
                        .build())
                .toList();
    }

    @Override
    public BookResponseDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .price(book.getPrice())
                .authorName(book.getAuthor().getFullName())
                .build();
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.setTitle(bookRequestDto.getTitle());
        book.setPrice(bookRequestDto.getPrice());
        book.setAuthor(author);

        Book updatedBook = bookRepository.save(book);

        return BookResponseDto.builder()
                .id(updatedBook.getId())
                .title(updatedBook.getTitle())
                .price(updatedBook.getPrice())
                .authorName(updatedBook.getAuthor().getFullName())
                .build();
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
}
