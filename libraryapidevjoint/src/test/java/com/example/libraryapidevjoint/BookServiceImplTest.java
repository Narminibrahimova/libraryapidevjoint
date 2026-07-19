package com.example.libraryapidevjoint;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;
import com.example.libraryapidevjoint.entity.Author;
import com.example.libraryapidevjoint.entity.Book;
import com.example.libraryapidevjoint.repository.AuthorRepository;
import com.example.libraryapidevjoint.repository.BookRepository;
import com.example.libraryapidevjoint.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void createBook_ShouldReturnBookResponse() {

        BookRequestDto request = new BookRequestDto();
        request.setTitle("Java");
        request.setPrice(25);
        request.setAuthorId(1L);
        Author author = Author.builder()
                .id(1L)
                .fullName("James Gosling")
                .build();
        Book savedBook = Book.builder()
                .id(1L)
                .title("Java")
                .price(25)
                .author(author)
                .build();
        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class)))
                .thenReturn(savedBook);
        BookResponseDto response = bookService.create(request);
        assertEquals(1L, response.getId());
        assertEquals("Java", response.getTitle());
        assertEquals(25, response.getPrice());
        assertEquals("James Gosling", response.getAuthorName());
        verify(authorRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}
