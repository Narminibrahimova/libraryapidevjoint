package com.example.libraryapidevjoint;

import com.example.libraryapidevjoint.dto.request.BookRequestDto;
import com.example.libraryapidevjoint.dto.response.BookResponseDto;
import com.example.libraryapidevjoint.entity.Author;
import com.example.libraryapidevjoint.entity.Book;
import com.example.libraryapidevjoint.entity.Category;
import com.example.libraryapidevjoint.exception.ResourceNotFoundException;
import com.example.libraryapidevjoint.mapper.BookMapper;
import com.example.libraryapidevjoint.repository.AuthorRepository;
import com.example.libraryapidevjoint.repository.BookRepository;
import com.example.libraryapidevjoint.repository.CategoryRepository;
import com.example.libraryapidevjoint.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void createBook_ShouldReturnBookResponse() {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("Java");
        request.setPrice(25);
        request.setAuthorId(1L);
        request.setCategoryIds(List.of(1L));

        Author author = Author.builder()
                .id(1L)
                .fullName("James Gosling")
                .build();
        Category category = Category.builder()
                .id(1L)
                .name("Programming")
                .build();
        Book bookEntity = Book.builder()
                .title("Java")
                .price(25)
                .build();
        Book savedBook = Book.builder()
                .id(1L)
                .title("Java")
                .price(25)
                .author(author)
                .build();
        BookResponseDto expectedResponse = BookResponseDto.builder()
                .id(1L)
                .title("Java")
                .price(25.0)
                .authorName("James Gosling")
                .build();

        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(author));
        when(categoryRepository.findAllById(List.of(1L)))
                .thenReturn(List.of(category));
        when(bookMapper.toEntity(request))
                .thenReturn(bookEntity);
        when(bookRepository.save(any(Book.class)))
                .thenReturn(savedBook);
        when(bookMapper.toDto(savedBook))
                .thenReturn(expectedResponse);

        BookResponseDto response = bookService.create(request);

        assertEquals(1L, response.getId());
        assertEquals("Java", response.getTitle());
        assertEquals(25, response.getPrice());
        assertEquals("James Gosling", response.getAuthorName());
        verify(authorRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findAllById(List.of(1L));
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void createBook_WhenAuthorNotFound_ShouldThrowException() {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("Java");
        request.setPrice(25);
        request.setAuthorId(1L);
        request.setCategoryIds(List.of(1L));

        when(authorRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                bookService.create(request));

        verify(authorRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any(Book.class));
    }
}
