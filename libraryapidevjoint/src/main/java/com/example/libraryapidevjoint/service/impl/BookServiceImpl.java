package com.example.libraryapidevjoint.service.impl;

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
import com.example.libraryapidevjoint.service.BookService;
import lombok.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.libraryapidevjoint.specification.BookSpecification.*;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;


    @Override
    public BookResponseDto create(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        List<Category> categories=categoryRepository.findAllById(bookRequestDto.getCategoryIds());
        if (categories.size()!=bookRequestDto.getCategoryIds().size()) {
            throw new ResourceNotFoundException("Some categories not found");
        }
        Book book = bookMapper.toEntity(bookRequestDto);
        book.setAuthor(author);
        book.setCategories(categories);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public Page<BookResponseDto> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(bookMapper::toDto);
    }

    @Override
    public BookResponseDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return bookMapper.toDto(book);
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        List<Category> categories=categoryRepository.findAllById(bookRequestDto.getCategoryIds());
        if (categories.size()!=bookRequestDto.getCategoryIds().size()) {
            throw new ResourceNotFoundException("Some categories not found");
        }
        bookMapper.updateEntity(bookRequestDto, book);
        book.setAuthor(author);
        book.setCategories(categories);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDto> filterBooks(
            String title,
            String author,
            String category,
            Double minPrice) {

        List<Book> books = bookRepository.filterBooks(
                title,
                author,
                category,
                minPrice
        );
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> searchBooks(
            String title,
            String author,
            String category,
            Double minPrice) {
        Specification<Book> specification =
                Specification
                        .where(hasTitle(title))
                        .and(hasAuthor(author))
                        .and(hasCategory(category))
                        .and(hasMinimumPrice(minPrice));
        List<Book> books = bookRepository.findAll(specification);
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
